package com.lrn.nettymysqlprotocol.protocol;

public enum MysqlCommandEnum {
    COM_SLEEP(0),
    COM_QUIT(1),
    COM_INIT_DB(2),
    COM_QUERY(3),
    COM_FIELD_LIST(4),
    COM_CREATE_DB(5),
    COM_DROP_DB(6),
    COM_REFRESH(7),
    COM_SHUTDOWN(8),
    COM_STATISTICS(9),
    COM_PROCESS_INFO(10),
    COM_CONNECT(11),
    COM_PROCESS_KILL(12),
    COM_DEBUG(13),
    COM_PING(14),
    COM_TIME(15),
    COM_DELAYED_INSERT(16),
    COM_CHANGE_USER(17),
    COM_BINLOG_DUMP(18),
    COM_TABLE_DUMP(19),
    COM_CONNECT_OUT(20),
    COM_REGISTER_SLAVE(21),
    COM_STMT_PREPARE(22),
    COM_STMT_EXECUTE(23),
    COM_STMT_SEND_LONG_DATA(24),
    COM_STMT_CLOSE(25),
    COM_STMT_RESET(26),
    COM_SET_OPTION(27),
    COM_STMT_FETCH(28),
    COM_DAEMON(29),
    COM_BINLOG_DUMP_GTID(29),
    COM_RESET_CONNECTION(30),
    
    /* Must be last */
    COM_LOGIN(254),
    COM_END(255);
    
    protected int value;
    
    MysqlCommandEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
