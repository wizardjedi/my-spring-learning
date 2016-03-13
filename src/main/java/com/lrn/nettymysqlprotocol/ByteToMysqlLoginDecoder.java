package com.lrn.nettymysqlprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class ByteToMysqlLoginDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        if (bb.readableBytes() < 3) {
            return ;
        }
        
        long packetLength = MysqlByteBufUtil.readInt3(bb);
        
        if (bb.readableBytes() < packetLength) {
            return ;
        }
        
        
    }
    
}
