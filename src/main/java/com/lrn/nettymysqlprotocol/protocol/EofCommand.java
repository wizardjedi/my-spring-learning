package com.lrn.nettymysqlprotocol.protocol;

import io.netty.buffer.ByteBuf;

public class EofCommand extends BasePacket{

    protected long warnings;
    
    protected ServerStatusEnum[] status;
    
    @Override
    public long getPacketLength() {
        return -1;
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        return null;
    }

    @Override
    public void readBody(ByteBuf bb) throws Exception {
        super.readBody(bb);
    }
    
}
