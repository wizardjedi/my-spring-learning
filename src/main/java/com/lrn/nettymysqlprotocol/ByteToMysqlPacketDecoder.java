package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class ByteToMysqlPacketDecoder extends ByteToMessageDecoder {

    public Decoder decoder = new Decoder();
    
    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        BasePacket bp = decoder.decode(bb);
        
        if (bp != null) {
            list.add(bp);
        }
    }
    
}
