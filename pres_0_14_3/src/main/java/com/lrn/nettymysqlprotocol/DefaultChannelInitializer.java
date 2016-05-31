package com.lrn.nettymysqlprotocol;

import static com.lrn.nettymysqlprotocol.App.logger;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.impl.InitialHandshakePacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultChannelInitializer extends ChannelInitializer<SocketChannel> {
    public static final Logger logger = LoggerFactory.getLogger(DefaultChannelInitializer.class);

    protected final static Random random = new Random();
    
    protected final static AtomicLong connectionId = new AtomicLong(0);
    
    protected MysqlServer server;

    public DefaultChannelInitializer(MysqlServer server) {
        this.server = server;
    }
    
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        logger.info("Client connected from:{}", ch.remoteAddress());

        server.getHandler().onClientConnect(ch);
        
        ByteBuf greetingBuffer = ch.alloc().buffer();

        MysqlTranscoder transcoder = new MysqlTranscoder();

        transcoder.setContext(new TranscoderContext());

        transcoder.getContext().getCapabilities().setCapabilities(0x807ff7ff);
        transcoder.getContext().getServerStatus().setStatus(0x0002);

        InitialHandshakePacket greet = new InitialHandshakePacket();
        
        random.setSeed(System.currentTimeMillis());
        
        byte[] scramble = new byte[20];
        
        random.nextBytes(scramble);
        
        greet.setScramble(scramble);
        greet.setCharacterSet(MysqlConstants.CharsetConstants.UTF8_GENERAL_CI);
                        
        greet.setConnectionId(connectionId.incrementAndGet());
        greet.setServerName(getServer().getServerDesription());
        greet.setSequenceNumber(0);

        transcoder.encode(greet, greetingBuffer);

        ch.writeAndFlush(greetingBuffer);

        AuthPhaseServerHandler authPhaseServerHandler = new AuthPhaseServerHandler(transcoder);        
        authPhaseServerHandler.setServer(server);
        
        ch.pipeline().addLast(authPhaseServerHandler);
                
        ch.closeFuture().addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {                                
                server.getHandler().onClientDisconnect(ch);                
            }
        });
    }

    public MysqlServer getServer() {
        return server;
    }

    public void setServer(MysqlServer server) {
        this.server = server;
    }
}
