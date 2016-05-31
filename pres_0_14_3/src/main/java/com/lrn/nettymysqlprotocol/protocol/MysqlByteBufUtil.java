package com.lrn.nettymysqlprotocol.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class MysqlByteBufUtil {

    /**
     * @see https://dev.mysql.com/doc/internals/en/integer.html
     * @return
     */
    public static int getInt1Length() {
        return 1;
    }

    public static int getInt2Length() {
        return 2;
    }

    public static int getInt3Length() {
        return 3;
    }

    public static ByteBuf writeInt1(ByteBuf byteBuffer, long value) {
        return writeInt(byteBuffer, value, 1);
    }

    public static ByteBuf writeInt2(ByteBuf byteBuffer, long value) {
        return writeInt(byteBuffer, value, 2);
    }

    public static ByteBuf writeInt3(ByteBuf byteBuffer, long value) {
        return writeInt(byteBuffer, value, 3);
    }

    public static ByteBuf writeInt4(ByteBuf byteBuffer, long value) {
        return writeInt(byteBuffer, value, 4);
    }

    public static ByteBuf writeInt(ByteBuf buffer, long value, int size) {
        long newValue = value;

        for (int i = 0; i < size; i++) {
            buffer.writeByte((int) (newValue & 0xFF));

            newValue = newValue >> 8;
        }

        return buffer;
    }

    public static long readInt1(ByteBuf buffer) {
        return readInt(buffer, 1);
    }

    public static long readInt2(ByteBuf buffer) {
        return readInt(buffer, 2);
    }

    public static long readInt3(ByteBuf buffer) {
        return readInt(buffer, 3);
    }

    public static long readInt4(ByteBuf buffer) {
        return readInt(buffer, 4);
    }

    public static long readInt(ByteBuf buffer, int size) {
        long value = 0;

        for (int i = 0; i < size; i++) {
            long readedByte = buffer.readUnsignedByte();

            long newValue = readedByte << (8 * i);

            value |= newValue;
        }

        return value;
    }

    public static int getStringLength(byte[] val) {
        return val.length;
    }

    /**
     * @see https://dev.mysql.com/doc/internals/en/string.html
     * @param buffer
     * @param value
     * @return
     */
    public static ByteBuf writeString(ByteBuf buffer, byte[] value) {
        if (value != null) {
            buffer.writeBytes(value);
        }
        return buffer;
    }

    public static int getNullTerminatedStringLength(byte[] value) {
        return 1 + (value == null ? 0 : value.length);
    }

    public static byte[] readEofString(ByteBuf bb, int len) {
        byte[] stringBuffer = new byte[len];

        bb.readBytes(stringBuffer, 0, len);

        return stringBuffer;
    }

    public static byte[] readNullTerminatedString(ByteBuf bb) {
        ByteBuf readBuffer = Unpooled.buffer();

        byte b;
        do {
            b = bb.readByte();

            if (b != 0x00) {
                readBuffer.writeByte(b);
            }
        } while (b != 0x00);

        byte[] result = new byte[readBuffer.readableBytes()];

        readBuffer.readBytes(result);

        return result;
    }

    public static ByteBuf writeNullTerminatedString(ByteBuf buffer, byte[] value) {
        if (value != null) {
            buffer.writeBytes(value);
        }

        buffer.writeByte(0x00);

        return buffer;
    }

    public static ByteBuf writeLengthedString(ByteBuf buffer, byte[] value, int len) {
        buffer.writeBytes(value, 0, len);

        return buffer;
    }

    /**
     * @see
     * https://dev.mysql.com/doc/internals/en/integer.html#packet-Protocol::LengthEncodedInteger
     * @param value
     * @return
     */
    public static long getLenencIntegerLength(long value) {
        if (value < 251) {
            return 1;
        } else if (value >= 251 && value < 65536) {
            return 3;
        } else if (value >= 65536 && value < 16777216) {
            return 4;
        } else {
            return 9;
        }
    }

    public static void writeStringEof(ByteBuf out, byte[] data) {
        out.writeBytes(data);
    }

    public static long getStringEofLength(byte[] data) {
        return data.length;
    }

    /**
     * @see
     * https://dev.mysql.com/doc/internals/en/string.html#packet-Protocol::LengthEncodedString
     * @param data
     * @return
     */
    public static long getLenencStringLength(byte[] data) {
        if (data == null) {
            return 1;
        } else if (data.length > 250) {
            if (data.length > 0xFFFFFF) {
                return 4 + data.length;
            } else if (data.length > 0xFFFF) {
                return 3 + data.length;
            } else {
                return 2 + data.length;
            }
        } else {
            return 1 + data.length;
        }
    }

    /**
     * @see
     * https://dev.mysql.com/doc/internals/en/integer.html#packet-Protocol::LengthEncodedInteger
     * @param out
     * @param value
     */
    public static void writeLenencInteger(ByteBuf out, long value) {
        if (value < 251) {
            MysqlByteBufUtil.writeInt(out, value, 1);
        } else if (value >= 251 && value < 65536) {
            out.writeByte(0xFC);
            MysqlByteBufUtil.writeInt(out, value, 2);
        } else if (value >= 65536 && value < 16777216) {
            out.writeByte(0xFD);
            MysqlByteBufUtil.writeInt(out, value, 3);
        } else {
            out.writeByte(0xFE);
            MysqlByteBufUtil.writeInt(out, value, 8);
        }
    }

    /**
     * @see
     * https://dev.mysql.com/doc/internals/en/string.html#packet-Protocol::LengthEncodedString
     * @param out
     * @param data
     */
    public static void writeLenencString(ByteBuf out, byte[] data) {
        if (data == null) {
            out.writeByte((byte) 251);
        } else if (data.length > 250) {
            if (data.length > 0xFFFFFF) {
                out.writeByte((byte) (254));
                MysqlByteBufUtil.writeInt(out, data.length, 4);

            } else if (data.length > 0xFFFF) {
                out.writeByte((byte) (253));
                MysqlByteBufUtil.writeInt(out, data.length, 3);
            } else {
                out.writeByte((byte) (252));
                MysqlByteBufUtil.writeInt(out, data.length, 2);
            }
            out.writeBytes(data);
        } else {
            out.writeByte((byte) data.length);
            out.writeBytes(data);
        }
    }
    
    public static void writeMysqlType(ByteBuf out, int type, Object value) {
        switch (type) {
            case MysqlConstants.TypesConstants.MYSQL_TYPE_STRING:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_VARCHAR: 
            case MysqlConstants.TypesConstants.MYSQL_TYPE_VAR_STRING:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_ENUM:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_SET:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_LONG_BLOB:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_MEDIUM_BLOB:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_BLOB:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_TINY_BLOB:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_GEOMETRY:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_BIT:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_DECIMAL:
            case MysqlConstants.TypesConstants.MYSQL_TYPE_NEWDECIMAL: 
                writeLenencString(out, value.toString().getBytes());
                break;            
                // case MysqlConstants.TypesConstants.MYSQL_TYPE_NEWDECIMAL:                
            default:                
        }
    }
    
}
