package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.server.ServerObject;

public interface MysqlServerHandler {
    void onClientConnect();
    
    void onClientDisconnect();
    
    void onLogin();
    
    ServerObject onQuery(String query);
}
