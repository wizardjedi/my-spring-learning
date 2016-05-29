package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;

/**
 * Base class for all packets
 */
public abstract class BasePacket implements Packet {

    protected int bodyLength = 0;
    protected int sequenceNumber = 0;

    public BasePacket() {
    }

    public BasePacket(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public BasePacket(long bodyLength) {
        this.bodyLength = (int)bodyLength;
    }

    @Override
    public void calculateAndSetBodyLength(TranscoderContext context) {
        this.bodyLength = calculateBodyLength(context);
    }

    @Override
    public int getBodyLength() {
        return bodyLength;
    }

    @Override
    public void setBodyLength(int packetBodyLength) {
        this.bodyLength = packetBodyLength;
    }

    @Override
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
