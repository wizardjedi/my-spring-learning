package com.lrn.nettymysqlprotocol;

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

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        logger.info("Client connected from:{}", ch.remoteAddress());

                        ByteBuf greetingBuffer = ch.alloc().buffer();

                        String greetString = "5b0000000a352e362e32382d307562"
                                + "756e7475302e31352e30342e31000b0000006527"
                                + "315e686e716b00fff70802007f80150000000000"
                                + "000000000048633c406a783d635d29513e006d79"
                                + "73716c5f6e61746976655f70617373776f726400";
                        
                        greetingBuffer
                            .writeBytes(
                                HexUtils
                                    .hexToByte(greetString)
                            );

                        ch.writeAndFlush(greetingBuffer);

                        ch.pipeline().addLast(new DiscardServerHandler());
                    }

                })
                .option(ChannelOption.SO_BACKLOG, 128) // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
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
