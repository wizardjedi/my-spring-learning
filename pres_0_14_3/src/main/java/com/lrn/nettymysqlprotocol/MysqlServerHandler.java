package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.server.ServerObject;
import io.netty.channel.Channel;

/**
 * 
 * @TODO: devide to connection handler and server handler
 */
public interface MysqlServerHandler {
    MysqlConnectionHandler onClientConnect(Channel channel);
    
    void onClientDisconnect(Channel channel);
    
    void onLogin();
}
