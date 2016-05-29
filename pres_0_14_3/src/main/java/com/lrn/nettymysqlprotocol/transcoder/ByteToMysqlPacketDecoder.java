package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class ByteToMysqlPacketDecoder extends ByteToMessageDecoder {

    public MysqlTranscoder transcoder = new MysqlTranscoder();

    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        Packet bp = transcoder.decode(bb);

        if (bp != null) {
            list.add(bp);
        }
    }

}
