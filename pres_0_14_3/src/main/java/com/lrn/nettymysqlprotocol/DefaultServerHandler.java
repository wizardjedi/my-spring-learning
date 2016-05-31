package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.impl.ComInitDbPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.ComQueryPacket;
import com.lrn.nettymysqlprotocol.server.ServerObject;
import com.lrn.nettymysqlprotocol.server.ServerObjectException;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultServerHandler extends ChannelInboundHandlerAdapter {

    public static final Logger logger = LoggerFactory.getLogger(DefaultServerHandler.class);

    protected MysqlTranscoder transcoder = null;
    
    protected MysqlServerHandler handler;
    
    public DefaultServerHandler(MysqlTranscoder transcoder) {
        this.transcoder = transcoder;
    }    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.debug("channel read");

        logger.debug("Read msg {}", msg);
                
        if (msg instanceof ComQueryPacket) {
            try {
                ServerObject serverObject = handler.onQuery(((ComQueryPacket) msg).getQuery());
                       
                ctx.channel().writeAndFlush(serverObject);
            } catch (ServerObjectException e) {
                ctx.channel().writeAndFlush(e);
            }
        }
        
        if (msg instanceof ComInitDbPacket) {
            try {
                ServerObject serverObject = handler.initDb(((ComInitDbPacket) msg).getSchemaName());
                       
                ctx.channel().writeAndFlush(serverObject);
            } catch (ServerObjectException e) {
                ctx.channel().writeAndFlush(e);
            }
        }
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

    public MysqlServerHandler getHandler() {
        return handler;
    }

    public void setHandler(MysqlServerHandler handler) {
        this.handler = handler;
    }
    
    
}
