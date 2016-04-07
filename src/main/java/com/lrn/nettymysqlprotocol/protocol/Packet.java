package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 * Base interface for all packets
 */
public interface Packet {
    /**
     * Read body from byte buffer
     * @param bb 
     * @throws java.lang.Exception 
     */
    void readBody(ByteBuf bb, TranscoderContext context) throws Exception;
    
    /**
     * Write body to byte buffer
     * @param bb 
     * @param context 
     * @throws java.lang.Exception 
     */
    void writeBody(ByteBuf bb, TranscoderContext context) throws Exception;
    
    /**
     * Calculate and return body size in bytes
     * @param context
     * @return 
     */
    int calculateBodyLength(TranscoderContext context);
    
    /**
     * Calculate and set field with body length
     * @param context
     */
    void calculateAndSetBodyLength(TranscoderContext context);
    
    /**
     * Get body length from field
     * @return 
     */
    int getBodyLength();

    /**
     * Get sequence number field value
     * @return 
     */
    int getSequenceNumber();
    
    /**
     * Set sequence number field value
     * @param sequenceNumber 
     */
    void setSequenceNumber(int sequenceNumber);    
}
