package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.impl.InitialHandshakePacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    public static final Logger logger = LoggerFactory.getLogger(App.class);

    public static int port = 1234;

    public static void main(String[] args) {
        logger.info("Application start");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        logger.info("Client connected from:{}", ch.remoteAddress());

                        ByteBuf greetingBuffer = ch.alloc().buffer();

                        MysqlTranscoder transcoder = new MysqlTranscoder();
                        
                        transcoder.setContext(new TranscoderContext());
                        
                        transcoder.getContext().getCapabilities().setCapabilities(0x807ff7ff);
                        transcoder.getContext().getServerStatus().setStatus(0x0002);
                        
                        InitialHandshakePacket greet = new InitialHandshakePacket();
                        greet.setScramble("01234567890123456789".getBytes());
                        greet.setCharacterSet(MysqlConstants.CharsetConstants.UTF8_GENERAL_CI);
                        greet.setConnectionId(11);
                        greet.setServerName("asdasd");
                        greet.setSequenceNumber(0);
                        
                        transcoder.encode(greet, greetingBuffer);

                        ch.writeAndFlush(greetingBuffer);
                        
                        ch.pipeline().addLast(new DiscardServerHandler(transcoder));
                    }

                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Error on create bootstrap", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

        logger.info("Application shutted down");
    }
}
