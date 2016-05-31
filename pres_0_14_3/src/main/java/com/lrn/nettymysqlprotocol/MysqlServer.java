package com.lrn.nettymysqlprotocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlServer {
    public static final Logger logger = LoggerFactory.getLogger(App.class);
    
    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workerGroup;
    protected int port = 3306;
    protected MysqlServerHandler handler;
    protected String serverName = "AMCF(A1S MySQL connection framework)";
    protected String version = "0.0.1";
    
    public MysqlServer() {
    }
    
    public MysqlServer(int port) {
        this.port = port;
    }
    
    public void run() {
        if (bossGroup == null) {
            bossGroup = new NioEventLoopGroup();
        }
        
        if (workerGroup == null) {
            workerGroup = new NioEventLoopGroup();
        }
        
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new DefaultChannelInitializer(this))
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
    }

    public String getServerDesription() {
        return getServerName()+"-"+getVersion();
    }
    
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }    
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public void setBossGroup(EventLoopGroup bossGroup) {
        this.bossGroup = bossGroup;
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public void setWorkerGroup(EventLoopGroup workerGroup) {
        this.workerGroup = workerGroup;
    }

    public MysqlServerHandler getHandler() {
        return handler;
    }

    public void setHandler(MysqlServerHandler handler) {
        this.handler = handler;
    }    
}
