package com.lrn.nettymysqlprotocol.protocol;

public class MysqlConstants {
    
    /**
     * @see https://dev.mysql.com/doc/internals/en/text-protocol.html
     * @see https://github.com/mysql/mysql-server/blob/HEAD/include/my_command.h
     */
    public static class CommandConstants {
        public static final int COM_SLEEP = 0;  // @see https://dev.mysql.com/doc/internals/en/com-sleep.html
        public static final int COM_QUIT = 1;   // @see https://dev.mysql.com/doc/internals/en/com-quit.html
        public static final int COM_INIT_DB = 2; // @see https://dev.mysql.com/doc/internals/en/com-init-db.html
        public static final int COM_QUERY = 3;   // @see https://dev.mysql.com/doc/internals/en/com-query.html
        public static final int COM_FIELD_LIST = 4; // @see https://dev.mysql.com/doc/internals/en/com-field-list.html
        public static final int COM_CREATE_DB = 5;  // @see https://dev.mysql.com/doc/internals/en/com-create-db.html
        public static final int COM_DROP_DB = 6;    // @see https://dev.mysql.com/doc/internals/en/com-drop-db.html
        public static final int COM_REFRESH = 7;    // @see https://dev.mysql.com/doc/internals/en/com-refresh.html
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
        public static final int COM_STMT_PREPARE = 22;  // @see https://dev.mysql.com/doc/internals/en/com-stmt-prepare.html
        public static final int COM_STMT_EXECUTE = 23;  // @see https://dev.mysql.com/doc/internals/en/com-stmt-execute.html
        public static final int COM_STMT_SEND_LONG_DATA = 24; // @see https://dev.mysql.com/doc/internals/en/com-stmt-send-long-data.html
        public static final int COM_STMT_CLOSE = 25; // @see https://dev.mysql.com/doc/internals/en/com-stmt-close.html
        public static final int COM_STMT_RESET = 26; // @see https://dev.mysql.com/doc/internals/en/com-stmt-reset.html
        public static final int COM_SET_OPTION = 27; // @see https://dev.mysql.com/doc/internals/en/com-set-option.html
        public static final int COM_STMT_FETCH = 28; // @see https://dev.mysql.com/doc/internals/en/com-stmt-fetch.html
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
        
        public static final long BIT_NUM_CLIENT_LONG_PASSWORD = 0;        /* new more secure passwords */
        public static final long BIT_NUM_CLIENT_FOUND_ROWS = 1;           /* Found instead of affected rows */
        public static final long BIT_NUM_CLIENT_LONG_FLAG = 2;            /* Get all column flags */
        public static final long BIT_NUM_CLIENT_CONNECT_WITH_DB = 3;      /* One can specify db on connect */
        public static final long BIT_NUM_CLIENT_NO_SCHEMA = 4;           /* Don't allow database.table.column */
        public static final long BIT_NUM_CLIENT_COMPRESS = 5;            /* Can use compression protocol */
        public static final long BIT_NUM_CLIENT_ODBC = 6;                /* Odbc client */
        public static final long BIT_NUM_CLIENT_LOCAL_FILES = 7;        /* Can use LOAD DATA LOCAL */
        public static final long BIT_NUM_CLIENT_IGNORE_SPACE = 8;       /* Ignore spaces before left brace */
        public static final long BIT_NUM_CLIENT_PROTOCOL_41 = 9;        /* New 4.1 protocol */
        public static final long BIT_NUM_CLIENT_INTERACTIVE = 10;       /* This is an interactive client */
        public static final long BIT_NUM_CLIENT_SSL = 11;               /* Switch to SSL after handshake */
        public static final long BIT_NUM_CLIENT_IGNORE_SIGPIPE = 12;    /* IGNORE sigpipes */
        public static final long BIT_NUM_CLIENT_TRANSACTIONS = 13;      /* Client knows about transactions */
        public static final long BIT_NUM_CLIENT_RESERVED = 14;         /* Old flag for 4.1 protocol  */
        public static final long BIT_NUM_CLIENT_SECURE_CONNECTION = 15;    /* Old flag for 4.1 authentication */
        public static final long BIT_NUM_CLIENT_MULTI_STATEMENTS = 16;  /* Enable/disable multi-stmt support */
        public static final long BIT_NUM_CLIENT_MULTI_RESULTS = 17;     /* Enable/disable multi-results */
        public static final long BIT_NUM_CLIENT_PS_MULTI_RESULTS = 18;  /* Multi-results in PS-protocol */
        public static final long BIT_NUM_CLIENT_PLUGIN_AUTH = 19;       /* Client supports plugin authentication */
        public static final long BIT_NUM_CLIENT_CONNECT_ATTRS = 20;     /* Client supports connection attributes */
        public static final long BIT_NUM_CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA = 21;
        public static final long BIT_NUM_CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS = 22;
        public static final long BIT_NUM_CLIENT_SESSION_TRACK = 23;
        public static final long BIT_NUM_CLIENT_DEPRECATE_EOF = 24;
        public static final long BIT_NUM_CLIENT_SSL_VERIFY_SERVER_CERT = 30;
        public static final long BIT_NUM_CLIENT_REMEMBER_OPTIONS = 31;
        
        public static final String DESCRIPTION_CLIENT_LONG_PASSWORD = "CLIENT_LONG_PASSWORD: Use the improved version of Old Password Authentication.";
        public static final String DESCRIPTION_CLIENT_FOUND_ROWS = "CLIENT_FOUND_ROWS: Send found rows instead of affected rows in EOF_Packet.";
        public static final String DESCRIPTION_CLIENT_LONG_FLAG = "CLIENT_LONG_FLAG: Longer flags in Protocol::ColumnDefinition320.";
        public static final String DESCRIPTION_CLIENT_CONNECT_WITH_DB = "CLIENT_CONNECT_WITH_DB:Database (schema) name can be specified on connect in Handshake Response Packet.";
        public static final String DESCRIPTION_CLIENT_NO_SCHEMA = "CLIENT_NO_SCHEMA: Do not permit database.table.column. ";         
        public static final String DESCRIPTION_CLIENT_COMPRESS = "CLIENT_COMPRESS: Compression protocol supported. ";          
        public static final String DESCRIPTION_CLIENT_ODBC = "CLIENT_ODBC: Special handling of ODBC behavior. ";              
        public static final String DESCRIPTION_CLIENT_LOCAL_FILES = "CLIENT_LOCAL_FILES: Can use LOAD DATA LOCAL. ";       
        public static final String DESCRIPTION_CLIENT_IGNORE_SPACE = "CLIENT_IGNORE_SPACE: Let the parser ignore spaces before '('. ";      
        public static final String DESCRIPTION_CLIENT_PROTOCOL_41 = "CLIENT_PROTOCOL_41: Supports the 4.1 protocol. ";       
        public static final String DESCRIPTION_CLIENT_INTERACTIVE = "CLIENT_INTERACTIVE: wait_timeout versus wait_interactive_timeout. ";      
        public static final String DESCRIPTION_CLIENT_SSL = "CLIENT_SSL: Switch to SSL after sending the capability-flags. ";              
        public static final String DESCRIPTION_CLIENT_IGNORE_SIGPIPE = "CLIENT_IGNORE_SIGPIPE: Do not issue SIGPIPE if network failures occur (libmysqlclient only). ";   
        public static final String DESCRIPTION_CLIENT_TRANSACTIONS = "CLIENT_TRANSACTIONS: Can send status flags in EOF_Packet.";     
        public static final String DESCRIPTION_CLIENT_RESERVED = "CLIENT_RESERVED: Unused.";         
        public static final String DESCRIPTION_CLIENT_SECURE_CONNECTION = "CLIENT_SECURE_CONNECTION: Supports Authentication::Native41."; 
        public static final String DESCRIPTION_CLIENT_MULTI_STATEMENTS = "CLIENT_MULTI_STATEMENTS: Can handle multiple statements per COM_QUERY and COM_STMT_PREPARE.";  
        public static final String DESCRIPTION_CLIENT_MULTI_RESULTS = "CLIENT_MULTI_RESULTS: Can handle multiple resultsets for COM_QUERY.";     
        public static final String DESCRIPTION_CLIENT_PS_MULTI_RESULTS = "CLIENT_PS_MULTI_RESULTS: Can handle multiple resultsets for COM_STMT_EXECUTE.";  
        public static final String DESCRIPTION_CLIENT_PLUGIN_AUTH = "CLIENT_PLUGIN_AUTH: Sends extra data in Initial Handshake Packet and supports the pluggable authentication protocol.";       
        public static final String DESCRIPTION_CLIENT_CONNECT_ATTRS = "CLIENT_CONNECT_ATTRS: Permits connection attributes in Protocol::HandshakeResponse41.";     
        public static final String DESCRIPTION_CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA = "CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA: Understands length-encoded integer for auth response data in Protocol::HandshakeResponse41.";
        public static final String DESCRIPTION_CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS = "CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS: Can handle expired passwords.";
        public static final String DESCRIPTION_CLIENT_SESSION_TRACK = "CLIENT_SESSION_TRACK: Can set SERVER_SESSION_STATE_CHANGED in the Status Flags and send session-state change data after a OK packet.";
        public static final String DESCRIPTION_CLIENT_DEPRECATE_EOF = "CLIENT_DEPRECATE_EOF: Expects an OK (instead of EOF) after the resultset rows of a Text Resultset.";
        public static final String DESCRIPTION_CLIENT_SSL_VERIFY_SERVER_CERT = "CLIENT_SSL_VERIFY_SERVER_CERT:";
        public static final String DESCRIPTION_CLIENT_REMEMBER_OPTIONS = "CLIENT_REMEMBER_OPTIONS:";
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
                
        public static final long BIT_NUM_SERVER_STATUS_IN_TRANS = 0; //a transaction is active
        public static final long BIT_NUM_SERVER_STATUS_AUTOCOMMIT = 1;	//auto-commit is enabled
        public static final long BIT_NUM_SERVER_MORE_RESULTS_EXISTS = 3;
        public static final long BIT_NUM_SERVER_STATUS_NO_GOOD_INDEX_USED = 4;
        public static final long BIT_NUM_SERVER_STATUS_NO_INDEX_USED = 5;
        public static final long BIT_NUM_SERVER_STATUS_CURSOR_EXISTS = 6;    //	Used by Binary Protocol Resultset to signal that COM_STMT_FETCH must be used to fetch the row-data.
        public static final long BIT_NUM_SERVER_STATUS_LAST_ROW_SENT = 7;
        public static final long BIT_NUM_SERVER_STATUS_DB_DROPPED = 8;
        public static final long BIT_NUM_SERVER_STATUS_NO_BACKSLASH_ESCAPES = 9;
        public static final long BIT_NUM_SERVER_STATUS_METADATA_CHANGED = 10;
        public static final long BIT_NUM_SERVER_QUERY_WAS_SLOW = 11;
        public static final long BIT_NUM_SERVER_PS_OUT_PARAMS	 = 12;
        public static final long BIT_NUM_SERVER_STATUS_IN_TRANS_READONLY = 13;	//in a read-only transaction
        public static final long BIT_NUM_SERVER_SESSION_STATE_CHANGED = 14;	//connection state information has changed
        
        public static final String DESCRIPTION_SERVER_STATUS_IN_TRANS = "SERVER_STATUS_IN_TRANS: a transaction is active";
        public static final String DESCRIPTION_SERVER_STATUS_AUTOCOMMIT = "SERVER_STATUS_AUTOCOMMIT: auto-commit is enabled";
        public static final String DESCRIPTION_SERVER_MORE_RESULTS_EXISTS = "SERVER_MORE_RESULTS_EXISTS:";
        public static final String DESCRIPTION_SERVER_STATUS_NO_GOOD_INDEX_USED = "SERVER_STATUS_NO_GOOD_INDEX_USED:";
        public static final String DESCRIPTION_SERVER_STATUS_NO_INDEX_USED = "SERVER_STATUS_NO_INDEX_USED:";
        public static final String DESCRIPTION_SERVER_STATUS_CURSOR_EXISTS = "SERVER_STATUS_CURSOR_EXISTS: Used by Binary Protocol Resultset to signal that COM_STMT_FETCH must be used to fetch the row-data.";
        public static final String DESCRIPTION_SERVER_STATUS_LAST_ROW_SENT = "SERVER_STATUS_LAST_ROW_SENT:";
        public static final String DESCRIPTION_SERVER_STATUS_DB_DROPPED = "SERVER_STATUS_DB_DROPPED:";
        public static final String DESCRIPTION_SERVER_STATUS_NO_BACKSLASH_ESCAPES = "SERVER_STATUS_NO_BACKSLASH_ESCAPES:";
        public static final String DESCRIPTION_SERVER_STATUS_METADATA_CHANGED = "SERVER_STATUS_METADATA_CHANGED:";
        public static final String DESCRIPTION_SERVER_QUERY_WAS_SLOW = "SERVER_QUERY_WAS_SLOW:";
        public static final String DESCRIPTION_SERVER_PS_OUT_PARAMS	 = "SERVER_PS_OUT_PARAMS:";
        public static final String DESCRIPTION_SERVER_STATUS_IN_TRANS_READONLY = "SERVER_STATUS_IN_TRANS_READONLY: in a read-only transaction";
        public static final String DESCRIPTION_SERVER_SESSION_STATE_CHANGED = "SERVER_SESSION_STATE_CHANGED: connection state information has changed";

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
