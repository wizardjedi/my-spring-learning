package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.server.ServerObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMysqlServerHandler implements MysqlServerHandler {

    public static final Logger logger = LoggerFactory.getLogger(DefaultMysqlServerHandler.class);
    
    @Override
    public void onClientConnect() {
        // Return success on connect
        
        // log event
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onClientDisconnect() {
        // log event
        
        throw new UnsupportedOperationException("Not supported yet.");
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
