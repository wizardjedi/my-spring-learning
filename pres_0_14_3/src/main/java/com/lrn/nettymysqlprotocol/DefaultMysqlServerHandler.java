package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.server.ServerObject;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMysqlServerHandler implements MysqlServerHandler {

    public static final Logger logger = LoggerFactory.getLogger(DefaultMysqlServerHandler.class);
    
    protected Channel channel;
    
    @Override
    public void onClientConnect(Channel channel) {
        this.channel = channel;
        
        logger.debug("Client connect with channel {}", channel);
    }

    @Override
    public void onClientDisconnect(Channel channel) {
        logger.debug("Client disconnect with channel {}", channel);
    }

    @Override
    public void onLogin() {
        // log event
        // return success code
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ServerObject onQuery(String query) {
        logger.info("Got query:{}", query);
        
        return null;
    }
    
}
