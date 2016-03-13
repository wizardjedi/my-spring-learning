package com.lrn.nettymysqlprotocol.protocol;

import io.netty.buffer.ByteBuf;
import java.io.UnsupportedEncodingException;

public class ComQueryPacket extends BasePacket {

    protected String query;
    
    @Override
    public long getPacketLength() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void readBody(ByteBuf byteBuf) {
        int queryLength = (int)(this.getLength() - 1);
        
        byte[] stringBuffer = new byte[queryLength];
        
        byteBuf.readBytes(stringBuffer, 0, queryLength);
        
        try {
            setQuery(new String(stringBuffer, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            /* */
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    
    
}
