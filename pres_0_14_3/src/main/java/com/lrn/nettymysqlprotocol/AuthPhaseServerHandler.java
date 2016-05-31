package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.impl.OkPacket;
import com.lrn.nettymysqlprotocol.transcoder.ByteToMysqlPacketDecoder;
import com.lrn.nettymysqlprotocol.transcoder.MysqlPacketToByteEncoder;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.ServerObjectToPacketEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthPhaseServerHandler extends ChannelInboundHandlerAdapter {

    public static final Logger logger = LoggerFactory.getLogger(AuthPhaseServerHandler.class);

    protected MysqlTranscoder transcoder = null;
    
    protected MysqlServer server;
    
    public AuthPhaseServerHandler(MysqlTranscoder transcoder) {
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
        
        ctx.pipeline().remove(this);
        
        transcoder.getContext().setCommandPhase();
        
        // inbound handlers
        ctx.pipeline().addFirst(new ByteToMysqlPacketDecoder(transcoder));
        DefaultServerHandler defaultServerHandler = new DefaultServerHandler(transcoder);
        defaultServerHandler.setHandler(getServer().getHandler());
        
        ctx.pipeline().addLast(defaultServerHandler);
        
        // out bound handlers
        ctx.pipeline().addFirst(new MysqlPacketToByteEncoder(transcoder));
        ctx.pipeline().addLast(new ServerObjectToPacketEncoder(transcoder));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.debug("Exception", cause);

        cause.printStackTrace();
        ctx.close();
    }

    public MysqlTranscoder getTranscoder() {
        return transcoder;
    }

    public void setTranscoder(MysqlTranscoder transcoder) {
        this.transcoder = transcoder;
    }

    public MysqlServer getServer() {
        return server;
    }

    public void setServer(MysqlServer server) {
        this.server = server;
    }
}
