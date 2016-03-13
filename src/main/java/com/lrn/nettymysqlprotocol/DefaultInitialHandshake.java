package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.CharacterSetEnum;
import com.lrn.nettymysqlprotocol.protocol.InitialHandshake;
import com.lrn.nettymysqlprotocol.protocol.ServerStatusEnum;
import java.util.UUID;

public class DefaultInitialHandshake extends InitialHandshake {

    protected static long connectionId = 1;

    public DefaultInitialHandshake() {
        this.serverCapabilities = MysqlByteBufUtil.longToServerCapabilities(0x807ff7ff);

        this.serverStatus = new ServerStatusEnum[] {
            ServerStatusEnum.SERVER_STATUS_AUTOCOMMIT
        };

        this.setConnectionId(connectionId++);

        this.setServerVersion("Fake MYSQL Server v1.0");

        this.authPluginName = "mysql_native_password";

        this.characterSet = CharacterSetEnum.utf8_general_ci;

        this.authPluginData = UUID.randomUUID().toString();
    }
}
