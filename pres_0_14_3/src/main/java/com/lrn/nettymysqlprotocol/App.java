package com.lrn.nettymysqlprotocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    public static final Logger logger = LoggerFactory.getLogger(App.class);

    public static int port = 1234;

    public static void main(String[] args) {
        logger.info("Application start");

        MysqlServer mysqlServer = new MysqlServer(port);
        mysqlServer.setHandler(new CustomMysqlServerHandler());
        mysqlServer.run();
        
        logger.info("Application shutted down");
    }
}
