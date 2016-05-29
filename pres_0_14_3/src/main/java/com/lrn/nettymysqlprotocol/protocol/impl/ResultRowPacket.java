package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-ProtocolText::ResultsetRow
 */
public class ResultRowPacket extends BasePacket {
    protected List<String> values;

    protected int columnCount;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        throw new NotImplementedException();
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        for (String value:getValues()) {
            if (value == null) {
                bb.writeByte(0xfb);
            } else {
                MysqlByteBufUtil.writeLenencString(bb, value.getBytes());
            }
        }
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        int length = 0;

        for (String value:getValues()) {
            length += MysqlByteBufUtil.getLenencStringLength(value.getBytes());
        }

        return length;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
