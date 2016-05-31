package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.server.ServerObject;
import io.netty.channel.Channel;

public interface MysqlServerHandler {
    void onClientConnect(Channel channel);
    
    void onClientDisconnect(Channel channel);
    
    void onLogin();
    
    ServerObject onQuery(String query);
}
