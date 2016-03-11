package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.ServerCapabilitiesEnum;
import com.lrn.nettymysqlprotocol.protocol.ServerStatusEnum;
import io.netty.buffer.ByteBuf;


public class MysqlByteBufUtil {
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

        for (int i=0;i<size;i++) {
            buffer.writeByte((int)(newValue & 0xFF));

            newValue = newValue >> 8;
        }

        return buffer;
    }

    public static ByteBuf writeString(ByteBuf buffer, String value) {
        buffer.writeBytes(value.getBytes());

        return buffer;
    }

    public static ByteBuf writeNullTerminatedString(ByteBuf buffer, String value) {
        buffer.writeBytes(value.getBytes());

        buffer.writeByte(0x00);

        return buffer;
    }

    public static long statusFlagsToLong(ServerStatusEnum [] statusFlags) {
        long value = 0;

        if (statusFlags !=null && statusFlags.length > 0) {
            for (int i=0;i<statusFlags.length;i++) {
                value |= statusFlags[i].getValue();
            }
        }

        return value;
    }

    public static long capabilityFlagsToLong(ServerCapabilitiesEnum[] capabilities) {
        long value = 0;

        if (capabilities !=null && capabilities.length > 0) {
            for (int i=0;i<capabilities.length;i++) {
                value |= capabilities[i].getValue();
            }
        }

        return value;
    }

    public static ServerCapabilitiesEnum[] longToServerCapabilities(long value) {
        int length = 0;

        for (ServerCapabilitiesEnum capability: ServerCapabilitiesEnum.values()) {
            if ( (value & capability.getValue()) > 0) {
                length ++;
            }
        }

        if (length > 0) {
            ServerCapabilitiesEnum[] caps = new ServerCapabilitiesEnum[length];

            int i=0;

            for (ServerCapabilitiesEnum capability: ServerCapabilitiesEnum.values()) {
                if ( (value & capability.getValue()) > 0) {
                    caps[i] = capability;

                    i++;
                }
            }

            return caps;
        } else {
            return new ServerCapabilitiesEnum[]{};
        }
    }

    public static ServerStatusEnum[] longToServerStatus(long value) {
        int length = 0;

        for (ServerStatusEnum capability: ServerStatusEnum.values()) {
            if ( (value & capability.getValue()) > 0) {
                length ++;
            }
        }

        if (length > 0) {
            ServerStatusEnum[] caps = new ServerStatusEnum[length];

            int i=0;

            for (ServerStatusEnum capability: ServerStatusEnum.values()) {
                if ( (value & capability.getValue()) > 0) {
                    caps[i] = capability;

                    i++;
                }
            }

            return caps;
        } else {
            return new ServerStatusEnum[]{};
        }
    }


}
