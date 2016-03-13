package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.MysqlByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class OkCommand extends BasePacket {

    protected long affectedRows = 0;
    
    protected long lastInsertId = 0;
    
    protected long warnings = 0;

    public OkCommand() {
        this.packetNumber =1;
    }
    
    
    
    @Override
    public long getPacketLength() {
        return
            1 +
            MysqlByteBufUtil.getLenencIntegerLength(getAffectedRows()) +
            MysqlByteBufUtil.getLenencIntegerLength(getLastInsertId()) +
            2 +
            2; 
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        byteBuf.writeByte(0x00);
        
        MysqlByteBufUtil.writeLenencInteger(byteBuf, getAffectedRows());
        MysqlByteBufUtil.writeLenencInteger(byteBuf, getLastInsertId());

        MysqlByteBufUtil.writeInt2(byteBuf, 7);
        MysqlByteBufUtil.writeInt2(byteBuf, 0);
        
        return byteBuf;
    }

    @Override
    public void readBody(ByteBuf bb) throws Exception {
        
    }
    
    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        super.writePacket(byteBuf);

        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        return byteBuf;
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

    public long getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(long lastInsertId) {
        this.lastInsertId = lastInsertId;
    }
    
}
