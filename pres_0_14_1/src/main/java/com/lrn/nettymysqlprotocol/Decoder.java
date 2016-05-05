package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.ComQueryPacket;
import com.lrn.nettymysqlprotocol.protocol.LoginPacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlCommandEnum;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Decoder {
    
    public static final Logger logger = LoggerFactory.getLogger(Decoder.class);
    
    public static int PACKET_LENGTH_FIELD_SIZE = 3;
    
    public BasePacket decode(ByteBuf bb) {
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
        
        int type = bb.readUnsignedByte();
        
        logger.debug("type:{}", type);
        
        BasePacket packet = null;
        
        switch (type) {
            case 0x03 :
                packet = new ComQueryPacket();
                break;
            case 0x00:
                packet = new ComSleepPacket();
                break;
            default:
                return null;
        }
        
        packet.setLength(packetBodyLength);
        packet.setPacketNumber((int) packetNumber);
        
        System.out.println(""+packet);
        
        try {
            packet.readBody(bb);
            
            return packet;
        } catch (Exception ex) {
            System.out.println(""+ex);
            
            return null;
        }
    }
    
    public LoginPacket decodeLogin(ByteBuf bb) {
        if (bb.readableBytes() < PACKET_LENGTH_FIELD_SIZE ) {
            return null;
        }
        
        long packetBodyLength = MysqlByteBufUtil.readInt3(bb);
        
        long packetNumber = bb.readUnsignedByte();
        
        if (bb.readableBytes() < packetBodyLength) {
            return null;
        }
        
        LoginPacket lp = new LoginPacket();
        
        lp.setLength(packetBodyLength);
        lp.setPacketNumber((int) packetNumber);
        
        try {
            lp.readBody(bb);
        } catch (Exception e) {
            return null;
        }
        return lp;
    }
    
    
}
