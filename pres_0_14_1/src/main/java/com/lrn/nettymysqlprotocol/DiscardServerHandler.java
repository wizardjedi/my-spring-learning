package com.lrn.nettymysqlprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    public static final Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        logger.debug("channel read");

        ByteBuf byteBuf = (ByteBuf)msg;

        if (logger.isTraceEnabled()) {
            logger.trace("\n{}", ByteBufUtil.prettyHexDump(byteBuf));
        }

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(HexUtil.toByteArray("0700000200000002000000"));

        ctx.writeAndFlush(buffer);

        // Discard the received data silently.
        ((ByteBuf) msg).release(); // (3)
        
        ctx.pipeline().remove(this);
        
        ctx.pipeline().addFirst(new MysqlPacketToByteEncoder());
        ctx.pipeline().addLast(new ByteToMysqlPacketDecoder());
        ctx.pipeline().addLast(new MessageProcessor());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        logger.debug("Exception", cause);

        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }


}
