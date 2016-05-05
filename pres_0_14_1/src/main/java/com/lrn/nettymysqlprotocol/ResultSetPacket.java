package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.EofCommand;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.List;

public class ResultSetPacket extends BasePacket {
    
    protected List<String> fields;
    
    protected List<List<String>> values;

    public ResultSetPacket() {
        this.packetNumber = 1;
    }
    
    @Override
    public long getPacketLength() {
        return MysqlByteBufUtil.getLenencIntegerLength(fields.size());
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        MysqlByteBufUtil.writeLenencInteger(byteBuf, fields.size());
        
        return byteBuf;
    }
    
    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        super.writePacket(byteBuf);

        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        int packetNum = 2;
        
        for (String field:getFields()) {
            FieldDefinitionPacket fdp = new FieldDefinitionPacket();
            
            fdp.setPacketNumber(packetNum);
            
            fdp.setName(field);
            
            System.out.println("WRITE");
            
            try {
                fdp.writePacket(byteBuf);
            } catch (Exception e) {
                System.out.println(""+e);
                e.printStackTrace();
            }
            
            System.out.println("FDP:"+fdp);
            
            packetNum++;
        }
        
        EofCommand eof1 = new EofCommand();
        eof1.setPacketNumber(packetNum);
        
        eof1.writePacket(byteBuf);
        
        packetNum++;
        
        for (List<String> row:getValues()) {
            ResultRowPacket rrp = new ResultRowPacket();
            rrp.setPacketNumber(packetNum);
            
            rrp.setValues(row);
            
            rrp.writePacket(byteBuf);
            
            packetNum++;
        }
        
        EofCommand eof2 = new EofCommand();
        eof2.setPacketNumber(packetNum);
        
        eof2.writePacket(byteBuf);
        
        return byteBuf;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<List<String>> getValues() {
        return values;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }
    
    
    
}
