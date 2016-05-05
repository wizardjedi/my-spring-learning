package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;

/**
 * Base class for all packets
 */
public abstract class BasePacket implements Packet {

    protected int bodyLength = 0;
    protected int sequenceNumber = 0;
    
    @Override
    public void calculateAndSetBodyLength(TranscoderContext context) {
        this.bodyLength = calculateBodyLength(context);
    }

    @Override
    public int getBodyLength() {
        return bodyLength;
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
