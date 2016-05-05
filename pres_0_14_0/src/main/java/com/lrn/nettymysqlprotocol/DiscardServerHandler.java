package com.lrn.nettymysqlprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    public static final Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.debug("channel read");

        ByteBuf byteBuf = (ByteBuf)msg;

        if (logger.isTraceEnabled()) {
            logger.trace("\n{}", ByteBufUtil.prettyHexDump(byteBuf));
        }

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes("Hello\n".getBytes());

        ctx.writeAndFlush(buffer);
        
        ((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.debug("Exception", cause);

        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }


}
