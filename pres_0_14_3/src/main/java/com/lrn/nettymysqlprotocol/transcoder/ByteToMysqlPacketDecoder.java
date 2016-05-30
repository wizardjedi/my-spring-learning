package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.DefaultServerHandler;
import com.lrn.nettymysqlprotocol.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteToMysqlPacketDecoder extends ByteToMessageDecoder {

    public static final Logger logger = LoggerFactory.getLogger(ByteToMysqlPacketDecoder.class);
    
    public MysqlTranscoder transcoder = new MysqlTranscoder();

    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        Packet bp = transcoder.decode(bb);

        if (bp != null) {
            list.add(bp);
        }
    }

}
