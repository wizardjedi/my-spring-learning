package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;

public class MysqlTypeMapper {
    public static int getMysqlType(int value) {
        return MysqlConstants.TypesConstants.MYSQL_TYPE_LONG;
    }

    public static int getMysqlType(Integer value) {
        return MysqlConstants.TypesConstants.MYSQL_TYPE_LONG;
    }

    public static int getMysqlType(String value) {
        return MysqlConstants.TypesConstants.MYSQL_TYPE_STRING;
    }

    public static int getMysqlType(byte[] value) {
        return MysqlConstants.TypesConstants.MYSQL_TYPE_BLOB;
    }

    /*

    MYSQL_TYPE_DECIMAL 	0x00 	Implemented by ProtocolBinary::MYSQL_TYPE_DECIMAL (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_DECIMAL)
    MYSQL_TYPE_TINY 	0x01 	Implemented by ProtocolBinary::MYSQL_TYPE_TINY (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_TINY)
    MYSQL_TYPE_SHORT 	0x02 	Implemented by ProtocolBinary::MYSQL_TYPE_SHORT (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_SHORT)
    MYSQL_TYPE_LONG 	0x03 	Implemented by ProtocolBinary::MYSQL_TYPE_LONG (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_LONG)
    MYSQL_TYPE_FLOAT 	0x04 	Implemented by ProtocolBinary::MYSQL_TYPE_FLOAT (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_FLOAT)
    MYSQL_TYPE_DOUBLE 	0x05 	Implemented by ProtocolBinary::MYSQL_TYPE_DOUBLE (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_DOUBLE)
    MYSQL_TYPE_NULL 	0x06 	Implemented by ProtocolBinary::MYSQL_TYPE_NULL (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_NULL)
    MYSQL_TYPE_TIMESTAMP 	0x07 	Implemented by ProtocolBinary::MYSQL_TYPE_TIMESTAMP (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_TIMESTAMP)
    MYSQL_TYPE_LONGLONG 	0x08 	Implemented by ProtocolBinary::MYSQL_TYPE_LONGLONG (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_LONGLONG)
    MYSQL_TYPE_INT24 	0x09 	Implemented by ProtocolBinary::MYSQL_TYPE_INT24 (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_INT24)
    MYSQL_TYPE_DATE 	0x0a 	Implemented by ProtocolBinary::MYSQL_TYPE_DATE (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_DATE)
    MYSQL_TYPE_TIME 	0x0b 	Implemented by ProtocolBinary::MYSQL_TYPE_TIME (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_TIME)
    MYSQL_TYPE_DATETIME 	0x0c 	Implemented by ProtocolBinary::MYSQL_TYPE_DATETIME (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_DATETIME)
    MYSQL_TYPE_YEAR 	0x0d 	Implemented by ProtocolBinary::MYSQL_TYPE_YEAR (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_YEAR)
    MYSQL_TYPE_NEWDATE [a] (https://dev.mysql.com/doc/internals/en/com-query-response.html#ftn.fnref_internal) 	0x0e 	see Protocol::MYSQL_TYPE_DATE (https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-Protocol::MYSQL_TYPE_DATE)
    MYSQL_TYPE_VARCHAR 	0x0f 	Implemented by ProtocolBinary::MYSQL_TYPE_VARCHAR (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_VARCHAR)
    MYSQL_TYPE_BIT 	0x10 	Implemented by ProtocolBinary::MYSQL_TYPE_BIT (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_BIT)
    MYSQL_TYPE_TIMESTAMP2 [a] (https://dev.mysql.com/doc/internals/en/com-query-response.html#ftn.fnref_internal) 	0x11 	see Protocol::MYSQL_TYPE_TIMESTAMP (https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-Protocol::MYSQL_TYPE_TIMESTAMP)
    MYSQL_TYPE_DATETIME2 [a] (https://dev.mysql.com/doc/internals/en/com-query-response.html#ftn.fnref_internal) 	0x12 	see Protocol::MYSQL_TYPE_DATETIME (https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-Protocol::MYSQL_TYPE_DATETIME)
    MYSQL_TYPE_TIME2 [a] (https://dev.mysql.com/doc/internals/en/com-query-response.html#ftn.fnref_internal) 	0x13 	see Protocol::MYSQL_TYPE_TIME (https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-Protocol::MYSQL_TYPE_TIME)
    MYSQL_TYPE_NEWDECIMAL 	0xf6 	Implemented by ProtocolBinary::MYSQL_TYPE_NEWDECIMAL (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_NEWDECIMAL)
    MYSQL_TYPE_ENUM 	0xf7 	Implemented by ProtocolBinary::MYSQL_TYPE_ENUM (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_ENUM)
    MYSQL_TYPE_SET 	0xf8 	Implemented by ProtocolBinary::MYSQL_TYPE_SET (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_SET)
    MYSQL_TYPE_TINY_BLOB 	0xf9 	Implemented by ProtocolBinary::MYSQL_TYPE_TINY_BLOB (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_TINY_BLOB)
    MYSQL_TYPE_MEDIUM_BLOB 	0xfa 	Implemented by ProtocolBinary::MYSQL_TYPE_MEDIUM_BLOB (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_MEDIUM_BLOB)
    MYSQL_TYPE_LONG_BLOB 	0xfb 	Implemented by ProtocolBinary::MYSQL_TYPE_LONG_BLOB (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_LONG_BLOB)
    MYSQL_TYPE_BLOB 	0xfc 	Implemented by ProtocolBinary::MYSQL_TYPE_BLOB (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_BLOB)
    MYSQL_TYPE_VAR_STRING 	0xfd 	Implemented by ProtocolBinary::MYSQL_TYPE_VAR_STRING (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_VAR_STRING)
    MYSQL_TYPE_STRING 	0xfe 	Implemented by ProtocolBinary::MYSQL_TYPE_STRING (https://dev.mysql.com/doc/internals/en/binary-protocol-value.html#packet-ProtocolBinary::MYSQL_TYPE_STRING)
    MYSQL_TYPE_GEOMETRY 	0xff


    */
}
