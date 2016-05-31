package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.impl.EofCommand;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-ProtocolText::Resultset
 */
public class ResultSetPacket extends BasePacket {

    protected int columnCount;   

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        throw new NotImplementedException();
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        MysqlByteBufUtil.writeLenencInteger(bb, getColumnCount());
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        return (int)MysqlByteBufUtil.getLenencIntegerLength(getColumnCount());
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }    
}
