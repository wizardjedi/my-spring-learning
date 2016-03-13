package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.MysqlCommandEnum;
import io.netty.util.AttributeKey;

public class ServerAttributes {
    final static AttributeKey<MysqlCommandEnum> COMMANDKEY = AttributeKey.valueOf("command");
}
