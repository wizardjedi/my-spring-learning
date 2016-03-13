package com.lrn.nettymysqlprotocol.protocol;

import io.netty.buffer.ByteBuf;

public class OkCommand extends BasePacket {

    protected long affectedRows;
    
    protected long warnings;
    
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
        
    }

    public long getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(long affectedRows) {
        this.affectedRows = affectedRows;
    }

    public long getWarnings() {
        return warnings;
    }

    public void setWarnings(long warnings) {
        this.warnings = warnings;
    }
    
    
    
    
}
