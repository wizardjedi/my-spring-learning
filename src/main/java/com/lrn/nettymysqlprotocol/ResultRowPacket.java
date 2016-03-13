package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.List;

public class ResultRowPacket extends BasePacket {
    protected List<String> values;

    @Override
    public long getPacketLength() {
        long length = 0;
        
        for (String value:getValues()) {
            length += MysqlByteBufUtil.getLenencStringLength(value.getBytes());
        }
        
        return length;
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        for (String value:getValues()) {
            MysqlByteBufUtil.writeLenencString(byteBuf, value.getBytes());
        }
        
        return byteBuf;
    }

    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        super.writePacket(byteBuf);

        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        return byteBuf;
    }
    
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
    
    
}
