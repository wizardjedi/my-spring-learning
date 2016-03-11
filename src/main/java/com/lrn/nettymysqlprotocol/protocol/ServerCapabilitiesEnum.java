package com.lrn.nettymysqlprotocol.protocol;

public enum ServerCapabilitiesEnum {

    CLIENT_LONG_PASSWORD(1),        /* new more secure passwords */
    CLIENT_FOUND_ROWS(2),           /* Found instead of affected rows */
    CLIENT_LONG_FLAG(4),            /* Get all column flags */
    CLIENT_CONNECT_WITH_DB(8),      /* One can specify db on connect */
    CLIENT_NO_SCHEMA(16),           /* Don't allow database.table.column */
    CLIENT_COMPRESS(32),            /* Can use compression protocol */
    CLIENT_ODBC(64),                /* Odbc client */
    CLIENT_LOCAL_FILES(128),        /* Can use LOAD DATA LOCAL */
    CLIENT_IGNORE_SPACE(256),       /* Ignore spaces before '(' */
    CLIENT_PROTOCOL_41(512),        /* New 4.1 protocol */
    CLIENT_INTERACTIVE(1024),       /* This is an interactive client */
    CLIENT_SSL(2048),               /* Switch to SSL after handshake */
    CLIENT_IGNORE_SIGPIPE(4096),    /* IGNORE sigpipes */
    CLIENT_TRANSACTIONS(8192),      /* Client knows about transactions */
    CLIENT_RESERVED(16384),         /* Old flag for 4.1 protocol  */
    CLIENT_RESERVED2(32768),        /* Old flag for 4.1 authentication */
    CLIENT_MULTI_STATEMENTS(1L << 16),  /* Enable/disable multi-stmt support */
    CLIENT_MULTI_RESULTS(1L << 17),     /* Enable/disable multi-results */
    CLIENT_PS_MULTI_RESULTS(1L << 18),  /* Multi-results in PS-protocol */
    CLIENT_PLUGIN_AUTH(1L << 19),       /* Client supports plugin authentication */
    CLIENT_CONNECT_ATTRS(1L << 20),     /* Client supports connection attributes */
    CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA(1L << 21),
    CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS(1L << 22),
    CLIENT_SESSION_TRACK(1L << 23),
    CLIENT_DEPRECATE_EOF(1L << 24),
    CLIENT_SSL_VERIFY_SERVER_CERT(1L << 30),
    CLIENT_REMEMBER_OPTIONS(1L << 31);

    protected long value;

    ServerCapabilitiesEnum(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
