package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.server.ServerObject;

public interface MysqlConnectionHandler {
    ServerObject onQuery(String query);
    
    ServerObject initDb(String schemaName);
}
