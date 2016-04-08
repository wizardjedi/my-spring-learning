package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.Packet;
import com.lrn.nettymysqlprotocol.protocol.impl.InitialHandshakePacket;
import com.lrn.nettymysqlprotocol.protocol.impl.OkPacket;
import io.netty.buffer.ByteBuf;

public class MysqlTranscoder {
    
    protected TranscoderContext context;

    public TranscoderContext getContext() {
        return context;
    }

    public void setContext(TranscoderContext context) {
        this.context = context;
    }
    
    /**
     * @see https://dev.mysql.com/doc/internals/en/mysql-packet.html
     * @param packet
     * @param bb
     * @throws Exception 
     */
    public void encode(Packet packet, ByteBuf bb) throws Exception {
        if (packet != null && bb != null && bb.isWritable()) {
            if (packet instanceof InitialHandshakePacket) {
                ((InitialHandshakePacket) packet).setCapabilities(getContext().getCapabilities());
                ((InitialHandshakePacket) packet).setStatus(getContext().getServerStatus());
            }
                                    
            packet.calculateAndSetBodyLength(context);

            MysqlByteBufUtil.writeInt3(bb, packet.getBodyLength());
            MysqlByteBufUtil.writeInt1(bb, packet.getSequenceNumber());
        
            packet.writeBody(bb, context);
        }
    }
}
