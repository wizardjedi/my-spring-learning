package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.MysqlByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 
 * @see https://dev.mysql.com/doc/internals/en/packet-EOF_Packet.html
 */
public class EofCommand extends BasePacket{

    protected long warnings;
    
    protected ServerStatusEnum[] status = new ServerStatusEnum[]{ServerStatusEnum.SERVER_STATUS_AUTOCOMMIT};
    
    @Override
    public long getPacketLength() {
        return 
            1       // header
            + 2     // warnings
            + 2;    // status flags
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        byteBuf.writeByte(0xfe);
        
        MysqlByteBufUtil.writeInt2(byteBuf, getWarnings());
        MysqlByteBufUtil.writeInt2(byteBuf, MysqlByteBufUtil.statusFlagsToLong(getStatus()));
        
        return byteBuf;
    }

    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        super.writePacket(byteBuf);

        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        return byteBuf;
    }
    
    @Override
    public void readBody(ByteBuf bb) throws Exception {
        setWarnings(MysqlByteBufUtil.readInt2(bb));
        setStatus(MysqlByteBufUtil.longToServerStatus(MysqlByteBufUtil.readInt2(bb)));
    }

    public long getWarnings() {
        return warnings;
    }

    public void setWarnings(long warnings) {
        this.warnings = warnings;
    }

    public ServerStatusEnum[] getStatus() {
        return status;
    }

    public void setStatus(ServerStatusEnum[] status) {
        this.status = status;
    }
    
    
}
