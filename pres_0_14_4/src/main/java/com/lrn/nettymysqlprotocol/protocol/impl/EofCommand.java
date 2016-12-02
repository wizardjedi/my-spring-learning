package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/packet-EOF_Packet.html
 */
public class EofCommand extends BasePacket{

    protected long warnings;

    protected ServerStatus status = new ServerStatus();

    public ServerStatus getStatus() {
        return status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    public long getWarnings() {
        return warnings;
    }

    public void setWarnings(long warnings) {
        this.warnings = warnings;
    }

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        setWarnings(MysqlByteBufUtil.readInt2(bb));
        getStatus().setStatus(MysqlByteBufUtil.readInt2(bb));
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        bb.writeByte(0xfe);

        MysqlByteBufUtil.writeInt2(bb, getWarnings());
        MysqlByteBufUtil.writeInt2(bb, context.getServerStatus().getStatus());
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        return
            1       // header
            + 2     // warnings
            + 2;    // status flags
    }


}
