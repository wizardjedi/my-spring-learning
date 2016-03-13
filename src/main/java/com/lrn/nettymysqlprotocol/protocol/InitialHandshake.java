package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.MysqlByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class InitialHandshake extends BasePacket {
    protected int protocolVersion = 0x0a;
    protected String serverVersion;
    protected long connectionId;
    protected String authPluginData;
    protected byte filler = 0x00;
    protected ServerCapabilitiesEnum[] serverCapabilities;
    protected CharacterSetEnum characterSet;
    protected ServerStatusEnum[] serverStatus;

    protected String authPluginName;

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    public String getAuthPluginData() {
        return authPluginData;
    }

    public void setAuthPluginData(String authPluginData) {
        this.authPluginData = authPluginData;
    }

    public byte getFiller() {
        return filler;
    }

    public void setFiller(byte filler) {
        this.filler = filler;
    }

    public ServerCapabilitiesEnum[] getServerCapabilities() {
        return serverCapabilities;
    }

    public void setServerCapabilities(ServerCapabilitiesEnum[] serverCapabilities) {
        this.serverCapabilities = serverCapabilities;
    }

    public CharacterSetEnum getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(CharacterSetEnum characterSet) {
        this.characterSet = characterSet;
    }

    public ServerStatusEnum[] getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatusEnum[] serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getAuthPluginName() {
        return authPluginName;
    }

    public void setAuthPluginName(String authPluginName) {
        this.authPluginName = authPluginName;
    }



    @Override
    public long getPacketLength() {
        long length = 0;
        
        length += 1; // protocol version
        
        length += getServerVersion().getBytes().length + 1; // server version + 0x00
        
        length += 4; // connection id

        length += 9; // auth data + 0x00

        length += 1; // filler

        length += 2; // lower 2 bytes of capacity

        length += 1; // charset

        length += 2; // status flags

        length += 2; // higher capacity

        length += 1; // length of auth data

        length += 10; // reserved 10 0x00 bytes

        length += 12; // auth data 2 + 0x00

        length += getAuthPluginName().getBytes().length + 1; // auth plugin name +1
        
        return length;
    }

    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        super.writePacket(byteBuf);

        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        return byteBuf;
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        long capabilities = MysqlByteBufUtil.capabilityFlagsToLong(getServerCapabilities());

        long lowerCapabilities = capabilities & 0xFFFF;
        long higherCapabilities = capabilities >> 16;

        long statusFlags = MysqlByteBufUtil.statusFlagsToLong(getServerStatus());

        // Write to buffer

        byteBuf.writeByte(getProtocolVersion());

        MysqlByteBufUtil.writeNullTerminatedString(byteBuf, getServerVersion());

        MysqlByteBufUtil.writeInt4(byteBuf, getConnectionId());

        MysqlByteBufUtil.writeString(byteBuf, getAuthPluginData().substring(0, 8));

        byteBuf.writeByte(getFiller());

        MysqlByteBufUtil.writeInt2(byteBuf, lowerCapabilities);

        byteBuf.writeByte(getCharacterSet().getValue());

        MysqlByteBufUtil.writeInt2(byteBuf, statusFlags);

        MysqlByteBufUtil.writeInt2(byteBuf, higherCapabilities);

        MysqlByteBufUtil.writeInt1(byteBuf, 21);

        byteBuf.writeBytes(new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});

        MysqlByteBufUtil.writeNullTerminatedString(byteBuf, getAuthPluginData().substring(8,20));

        MysqlByteBufUtil.writeNullTerminatedString(byteBuf, getAuthPluginName());

        return byteBuf;
    }

}
