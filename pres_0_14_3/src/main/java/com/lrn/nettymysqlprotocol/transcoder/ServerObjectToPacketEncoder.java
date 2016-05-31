package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.Packet;
import com.lrn.nettymysqlprotocol.protocol.impl.ColumnDefinitionPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.EofCommand;
import com.lrn.nettymysqlprotocol.protocol.impl.ErrPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.OkPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.ResultRowPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.ResultSetPacket;
import com.lrn.nettymysqlprotocol.server.ResultSet;
import com.lrn.nettymysqlprotocol.server.ServerObject;
import com.lrn.nettymysqlprotocol.server.ServerObjectException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerObjectToPacketEncoder extends MessageToMessageEncoder<ServerObject>  {
    public static final Logger logger = LoggerFactory.getLogger(ServerObjectToPacketEncoder.class);
    
    public MysqlTranscoder transcoder = new MysqlTranscoder();

    public ServerObjectToPacketEncoder(MysqlTranscoder transcoder) {
        this.transcoder = transcoder;
    }
    
    @Override
    protected void encode(ChannelHandlerContext chc, ServerObject serverObject, List<Object> list) throws Exception {
        if (serverObject instanceof ResultSet) {
            List<Packet> packets = processResultSet((ResultSet)serverObject);
            
            list.addAll(packets);
        }
        
        if (serverObject instanceof ServerObjectException) {
            ErrPacket errPacket = new ErrPacket();
            errPacket.setErrorCode(((ServerObjectException) serverObject).getErrorCode());
            errPacket.setErrorMessage(((ServerObjectException) serverObject).getErrorMessage());
            errPacket.setSqlState(((ServerObjectException) serverObject).getSqlState());
            errPacket.setSequenceNumber(1);
                        
            list.add(errPacket);
        }
    }
    
    protected List processResultSet(ResultSet rs) {
        List<Packet> resultList = new ArrayList<>();
        
        int sequenceNumber = 1;
        
        ResultSetPacket rsPacket = new ResultSetPacket();
        rsPacket.setSequenceNumber(sequenceNumber);
        rsPacket.setColumnCount(rs.getColumns().size());
        
        resultList.add(rsPacket);
        
        sequenceNumber++;
        
        for (int i=0;i<rsPacket.getColumnCount();i++) {
            ColumnDefinitionPacket fieldDefinitionPacket = new ColumnDefinitionPacket();

            fieldDefinitionPacket.setType(MysqlConstants.TypesConstants.MYSQL_TYPE_STRING);
            
            fieldDefinitionPacket.setName(rs.getColumns().get(i).getName());
            fieldDefinitionPacket.setOrg_name(rs.getColumns().get(i).getName());
            
            fieldDefinitionPacket.setSequenceNumber(sequenceNumber);
            
            resultList.add(fieldDefinitionPacket);
            
            sequenceNumber++;
        }
        
        EofCommand eof = new EofCommand();
        eof.setWarnings(0);
        eof.setStatus(transcoder.getContext().getServerStatus());
        eof.setSequenceNumber(sequenceNumber);
        
        resultList.add(eof);
        
        sequenceNumber++;
        
        for (ResultSet.Row row:rs.getRows()) {
            ResultRowPacket rrPacket = new ResultRowPacket();
            
            for (ResultSet.Value val:row.getValues()) {
                rrPacket.getValues().add((String)val.getValue());
            }
            
            rrPacket.setSequenceNumber(sequenceNumber);
            
            resultList.add(rrPacket);
            
            sequenceNumber++;
        }
        
        EofCommand eof2 = new EofCommand();
        eof2.setWarnings(0);
        eof2.setStatus(transcoder.getContext().getServerStatus());
        eof2.setSequenceNumber(sequenceNumber);
        
        resultList.add(eof2);
        
        return resultList;
    }
}
