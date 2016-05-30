package com.lrn.nettymysqlprotocol;

public interface MysqlServerHandler {
    void onClientConnect();
    
    void onClientDisconnect();
    
    void onLogin();
    
    void onQuery();
}
