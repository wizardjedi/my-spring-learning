package com.lrn.nettymysqlprotocol.protocol;

public class MysqlConstants {
    
    /**
     * @see https://dev.mysql.com/doc/internals/en/text-protocol.html
     * @see https://github.com/mysql/mysql-server/blob/HEAD/include/my_command.h
     */
    public static class CommandConstants {
        public static final int COM_SLEEP = 0;
        public static final int COM_QUIT = 1;
        public static final int COM_INIT_DB = 2;
        public static final int COM_QUERY = 3;
        public static final int COM_FIELD_LIST = 4;
        public static final int COM_CREATE_DB = 5;
        public static final int COM_DROP_DB = 6;
        public static final int COM_REFRESH = 7;
        public static final int COM_SHUTDOWN = 8;
        public static final int COM_STATISTICS = 9;
        public static final int COM_PROCESS_INFO = 10;
        public static final int COM_CONNECT = 11;
        public static final int COM_PROCESS_KILL = 12;
        public static final int COM_DEBUG = 13;
        public static final int COM_PING = 14;
        public static final int COM_TIME = 15;
        public static final int COM_DELAYED_INSERT = 16;
        public static final int COM_CHANGE_USER = 17;
        public static final int COM_BINLOG_DUMP = 18;
        public static final int COM_TABLE_DUMP = 19;
        public static final int COM_CONNECT_OUT = 20;
        public static final int COM_REGISTER_SLAVE = 21;
        public static final int COM_STMT_PREPARE = 22;
        public static final int COM_STMT_EXECUTE = 23;
        public static final int COM_STMT_SEND_LONG_DATA = 24;
        public static final int COM_STMT_CLOSE = 25;
        public static final int COM_STMT_RESET = 26;
        public static final int COM_SET_OPTION = 27;
        public static final int COM_STMT_FETCH = 28;
        public static final int COM_DAEMON = 29;
        public static final int COM_BINLOG_DUMP_GTID = 30;
        public static final int COM_RESET_CONNECTION = 31;
    }
    
    /**
     * @see https://dev.mysql.com/doc/internals/en/capability-flags.html#packet-Protocol::CapabilityFlags
     * @see https://github.com/mysql/mysql-server/blob/HEAD/include/mysql_com.h
     */
    public static class CapabilitiesConstants {
        public static final long CLIENT_LONG_PASSWORD = 1;        /* new more secure passwords */
        public static final long CLIENT_FOUND_ROWS = 2;           /* Found instead of affected rows */
        public static final long CLIENT_LONG_FLAG = 4;            /* Get all column flags */
        public static final long CLIENT_CONNECT_WITH_DB = 8;      /* One can specify db on connect */
        public static final long CLIENT_NO_SCHEMA = 16;           /* Don't allow database.table.column */
        public static final long CLIENT_COMPRESS = 32;            /* Can use compression protocol */
        public static final long CLIENT_ODBC = 64;                /* Odbc client */
        public static final long CLIENT_LOCAL_FILES = 128;        /* Can use LOAD DATA LOCAL */
        public static final long CLIENT_IGNORE_SPACE = 256;       /* Ignore spaces before left brace */
        public static final long CLIENT_PROTOCOL_41 = 512;        /* New 4.1 protocol */
        public static final long CLIENT_INTERACTIVE = 1024;       /* This is an interactive client */
        public static final long CLIENT_SSL = 2048;               /* Switch to SSL after handshake */
        public static final long CLIENT_IGNORE_SIGPIPE = 4096;    /* IGNORE sigpipes */
        public static final long CLIENT_TRANSACTIONS = 8192;      /* Client knows about transactions */
        public static final long CLIENT_RESERVED = 16384;         /* Old flag for 4.1 protocol  */
        public static final long CLIENT_SECURE_CONNECTION = 32768;    /* Old flag for 4.1 authentication */
        public static final long CLIENT_MULTI_STATEMENTS = 1L << 16;  /* Enable/disable multi-stmt support */
        public static final long CLIENT_MULTI_RESULTS = 1L << 17;     /* Enable/disable multi-results */
        public static final long CLIENT_PS_MULTI_RESULTS = 1L << 18;  /* Multi-results in PS-protocol */
        public static final long CLIENT_PLUGIN_AUTH = 1L << 19;       /* Client supports plugin authentication */
        public static final long CLIENT_CONNECT_ATTRS = 1L << 20;     /* Client supports connection attributes */
        public static final long CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA = 1L << 21;
        public static final long CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS = 1L << 22;
        public static final long CLIENT_SESSION_TRACK = 1L << 23;
        public static final long CLIENT_DEPRECATE_EOF = 1L << 24;
        public static final long CLIENT_SSL_VERIFY_SERVER_CERT = 1L << 30;
        public static final long CLIENT_REMEMBER_OPTIONS = 1L << 31;
    }
    
    /**
     * @see https://dev.mysql.com/doc/internals/en/character-set.html#packet-Protocol::CharacterSet
     * @see 
     */
    public static class CharsetConstants {
        public static final int BIG5_CHINESE_CI = 1;
        public static final int LATIN2_CZECH_CS = 2;
        public static final int DEC8_SWEDISH_CI = 3;
        public static final int CP850_GENERAL_CI = 4;
        public static final int LATIN1_GERMAN1_CI = 5;
        public static final int HP8_ENGLISH_CI = 6;
        public static final int KOI8R_GENERAL_CI = 7;
        public static final int LATIN1_SWEDISH_CI = 8;
        public static final int LATIN2_GENERAL_CI = 9;
        public static final int SWE7_SWEDISH_CI = 10;
        public static final int UTF8_GENERAL_CI = 33;
        public static final int BINARY = 63;
    }
    
    /**
     * @see https://dev.mysql.com/doc/internals/en/status-flags.html#packet-Protocol::StatusFlags
     * @see https://github.com/mysql/mysql-server/blob/HEAD/include/mysql_com.h
     */
    public static class StatusConstants {
        public static final long SERVER_STATUS_IN_TRANS = 0x0001; //a transaction is active
        public static final long SERVER_STATUS_AUTOCOMMIT = 0x0002;	//auto-commit is enabled
        public static final long SERVER_MORE_RESULTS_EXISTS = 0x0008;
        public static final long SERVER_STATUS_NO_GOOD_INDEX_USED = 0x0010;
        public static final long SERVER_STATUS_NO_INDEX_USED = 0x0020;
        public static final long SERVER_STATUS_CURSOR_EXISTS = 0x0040;    //	Used by Binary Protocol Resultset to signal that COM_STMT_FETCH must be used to fetch the row-data.
        public static final long SERVER_STATUS_LAST_ROW_SENT = 0x0080;
        public static final long SERVER_STATUS_DB_DROPPED = 0x0100;
        public static final long SERVER_STATUS_NO_BACKSLASH_ESCAPES = 0x0200;
        public static final long SERVER_STATUS_METADATA_CHANGED = 0x0400;
        public static final long SERVER_QUERY_WAS_SLOW = 0x0800;
        public static final long SERVER_PS_OUT_PARAMS	 = 0x1000;
        public static final long SERVER_STATUS_IN_TRANS_READONLY = 0x2000;	//in a read-only transaction
        public static final long SERVER_SESSION_STATE_CHANGED = 0x4000;	//connection state information has changed
    }
    
    /**
     * @see https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-ProtocolText::Resultset
     * @see https://dev.mysql.com/doc/internals/en/binary-protocol-value.html
     * @see https://github.com/mysql/mysql-server/blob/HEAD/libbinlogevents/export/binary_log_types.h
     */
    public static class TypesConstants {
        public static final int MYSQL_TYPE_DECIMAL = 0x00;
        public static final int MYSQL_TYPE_TINY = 0x01;
        public static final int MYSQL_TYPE_SHORT = 0x02;	
        public static final int MYSQL_TYPE_LONG = 0x03;
        public static final int MYSQL_TYPE_FLOAT = 0x04;
        public static final int MYSQL_TYPE_DOUBLE = 0x05;
        public static final int MYSQL_TYPE_NULL = 0x06;
        public static final int MYSQL_TYPE_TIMESTAMP = 0x07;
        public static final int MYSQL_TYPE_LONGLONG = 0x08;
        public static final int MYSQL_TYPE_INT24 = 0x09;
        public static final int MYSQL_TYPE_DATE = 0x0a;
        public static final int MYSQL_TYPE_TIME = 0x0b;
        public static final int MYSQL_TYPE_DATETIME = 0x0c;
        public static final int MYSQL_TYPE_YEAR = 0x0d;
        public static final int MYSQL_TYPE_NEWDATE = 0x0e;
        public static final int MYSQL_TYPE_VARCHAR = 0x0f;
        public static final int MYSQL_TYPE_BIT = 0x10;
        public static final int MYSQL_TYPE_TIMESTAMP2 = 0x11;
        public static final int MYSQL_TYPE_DATETIME2 = 0x12;
        public static final int MYSQL_TYPE_TIME2 = 0x13;
        public static final int MYSQL_TYPE_NEWDECIMAL = 0xf6;
        public static final int MYSQL_TYPE_ENUM = 0xf7;
        public static final int MYSQL_TYPE_SET = 0xf8;
        public static final int MYSQL_TYPE_TINY_BLOB = 0xf9;
        public static final int MYSQL_TYPE_MEDIUM_BLOB = 0xfa;
        public static final int MYSQL_TYPE_LONG_BLOB = 0xfb;
        public static final int MYSQL_TYPE_BLOB = 0xfc;
        public static final int MYSQL_TYPE_VAR_STRING = 0xfd;
        public static final int MYSQL_TYPE_STRING = 0xfe;
        public static final int MYSQL_TYPE_GEOMETRY = 0xff;
    }
}
