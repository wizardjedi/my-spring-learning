package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.ComQueryPacket;
import com.lrn.nettymysqlprotocol.protocol.LoginPacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlCommandEnum;
import io.netty.buffer.ByteBuf;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Decoder {
    public static int PACKET_LENGTH_FIELD_SIZE = 3;
    
    public BasePacket decode(ByteBuf bb) {
        if (bb.readableBytes() < PACKET_LENGTH_FIELD_SIZE ) {
            return null;
        }
        
        long packetBodyLength = MysqlByteBufUtil.readInt3(bb);
        
        long packetNumber = bb.readUnsignedByte();
        
        if (bb.readableBytes() < packetBodyLength) {
            return null;
        }
        
        int type = bb.readUnsignedByte();
        
        BasePacket packet = null;
        
        switch (type) {
            case 0x03 :
                packet = new ComQueryPacket();
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
