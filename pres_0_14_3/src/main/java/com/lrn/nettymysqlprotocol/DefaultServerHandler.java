package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.impl.OkPacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultServerHandler extends ChannelInboundHandlerAdapter {

    public static final Logger logger = LoggerFactory.getLogger(DefaultServerHandler.class);

    protected MysqlTranscoder transcoder = null;
    
    public DefaultServerHandler(MysqlTranscoder transcoder) {
        this.transcoder = transcoder;
    }    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.debug("channel read");

        ByteBuf byteBuf = (ByteBuf)msg;

        if (logger.isTraceEnabled()) {
            logger.trace("\n{}", ByteBufUtil.prettyHexDump(byteBuf));
        }

        OkPacket okPacket = new OkPacket();
        okPacket.setAffectedRows(0);

        okPacket.setSequenceNumber(2);
        
        ByteBuf buffer = ctx.alloc().buffer();
        
        try {
            transcoder.encode(okPacket, buffer);
        } catch (Exception e) {
            
        }
        
        logger.trace("\n{}", ByteBufUtil.prettyHexDump(buffer));
        
        ctx.writeAndFlush(buffer);
        
        ((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.debug("Exception", cause);

        cause.printStackTrace();
        ctx.close();
    }
}
