package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.MysqlByteBufUtil;
import io.netty.buffer.ByteBuf;


public abstract class BasePacket {
    protected boolean isRequest = false;

    protected long length;

    protected int packetNumber;

    public int getPacketNumber() {
        return packetNumber;
    }

    public void setPacketNumber(int packetNumber) {
        this.packetNumber = packetNumber;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isIsRequest() {
        return isRequest;
    }

    public void setIsRequest(boolean isRequest) {
        this.isRequest = isRequest;
    }

    public abstract long getPacketLength();

    public abstract ByteBuf encodePacket(ByteBuf byteBuf);

    public ByteBuf writePacket(ByteBuf byteBuf) {
        long length = getPacketLength();

        MysqlByteBufUtil.writeInt3(byteBuf, length);
        MysqlByteBufUtil.writeInt1(byteBuf, getPacketNumber());

        return byteBuf;
    }
    
    public void readBody(ByteBuf bb) throws Exception {
        
    }

}
