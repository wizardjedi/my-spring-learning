package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.Packet;
import com.lrn.nettymysqlprotocol.protocol.impl.ComInitDbPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.ComQueryPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.ComQuitPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.InitialHandshakePacket;
import com.lrn.nettymysqlprotocol.protocol.impl.LoginPacket;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlTranscoder {

    public static final Logger logger = LoggerFactory.getLogger(MysqlTranscoder.class);

    public static int PACKET_LENGTH_FIELD_SIZE = 3;

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

    public Packet decode(ByteBuf bb) {
        logger.debug("Readable bytes:{}", bb.readableBytes());

        if (bb.readableBytes() < PACKET_LENGTH_FIELD_SIZE + 1 ) {
            return null;
        }

        long packetBodyLength = MysqlByteBufUtil.readInt3(bb);

        long packetNumber = bb.readUnsignedByte();

        logger.debug("Readable bytes:{} packet len:{} packet num:{}", bb.readableBytes(), packetBodyLength,  packetNumber);

        if (bb.readableBytes() < packetBodyLength) {
            return null;
        }

        Packet packet;

        if (getContext().isCommandPhase()) {
            logger.trace("Context in command phase");
            
            int type = bb.readUnsignedByte();

            logger.debug("Packet type id:{}", type);

            switch (type) {
                case MysqlConstants.CommandConstants.COM_QUERY:
                    packet = new ComQueryPacket();
                    break;
                case MysqlConstants.CommandConstants.COM_QUIT:
                    packet = new ComQuitPacket();
                    break;
                case MysqlConstants.CommandConstants.COM_INIT_DB:
                    packet = new ComInitDbPacket();
                    break;                 
                default:
                    return null;
            }
        } else {
            if (getContext().isAuthPhase()) {
                logger.trace("Context in auth phase");
                
                packet = new LoginPacket();
            } else {
                logger.trace("Undefined phase");
                
                return null;
            }
        }

        packet.setSequenceNumber((int)packetNumber);
        packet.setBodyLength((int)packetBodyLength);

        try {
            packet.readBody(bb, getContext());

            return packet;
        } catch (Exception ex) {
            logger.error("Error on read packet body", ex);

            return null;
        }
    }
}
