package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

public class InitialHandshakePacket extends BasePacket {

    public static final String AUTH_PLUGIN_NAME = "mysql_native_password";
    
    protected int protocolVersion = 0x0a;
    protected String serverName = "Fake mysql server v1.0";
    protected long connectionId;
    protected byte[] scramble;
    protected Capabilities capabilities = new Capabilities();
    protected ServerStatus status = new ServerStatus();
    protected int characterSet = MysqlConstants.CharsetConstants.UTF8_GENERAL_CI;
    protected String authPluginName = AUTH_PLUGIN_NAME;

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    public byte[] getScramble() {
        return scramble;
    }

    public void setScramble(byte[] scramble) {
        this.scramble = scramble;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    public int getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(int characterSet) {
        this.characterSet = characterSet;
    }

    public String getAuthPluginName() {
        return authPluginName;
    }

    public void setAuthPluginName(String authPluginName) {
        this.authPluginName = authPluginName;
    }
    
    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        // [0a] protocol version
        MysqlByteBufUtil.writeInt1(bb, getProtocolVersion());
        //string[NUL]    server version
        MysqlByteBufUtil.writeNullTerminatedString(bb, getServerName().getBytes());
        //4              connection id
        MysqlByteBufUtil.writeInt4(bb, getConnectionId());
        //string[8]      auth-plugin-data-part-1
        MysqlByteBufUtil.writeLengthedString(bb, getScramble(), 8);
        //1              [00] filler
        MysqlByteBufUtil.writeInt1(bb, 0x00);
        //2              capability flags (lower 2 bytes)
        MysqlByteBufUtil.writeInt2(bb, getCapabilities().getLowWord());
        
        //1              character set
        MysqlByteBufUtil.writeInt1(bb, getCharacterSet());
        //2              status flags
        MysqlByteBufUtil.writeInt2(bb, getStatus().getStatus());
        //2              capability flags (upper 2 bytes)
        MysqlByteBufUtil.writeInt2(bb, getCapabilities().getHighWord());
        
        if (capabilities != null && capabilities.isClientPluginAuth()) {
            //1              length of auth-plugin-data
            MysqlByteBufUtil.writeInt1(bb, getAuthPluginName().getBytes().length);
        } else {
            //1              [00] filler
            MysqlByteBufUtil.writeInt1(bb, 0x00);
        }
        
        //string[10]     reserved (all [00])
        bb.writeZero(10);
        
        if (capabilities.isClientSecureConnection()) {
            // string[$len]   auth-plugin-data-part-2 ($len=MAX(13, length of auth-plugin-data - 8))
            bb.writeBytes(getScramble(), 8, 12);
            bb.writeByte(0x00);
        }
        
        if (capabilities.isClientPluginAuth()) {
            // string[NUL]    auth-plugin name
            MysqlByteBufUtil.writeNullTerminatedString(bb, getAuthPluginName().getBytes());
        }
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        int result = 0;
        
        // [0a] protocol version
        result += 1;
        //string[NUL]    server version
        result += MysqlByteBufUtil.getNullTerminatedStringLength(getServerName().getBytes());
        //4              connection id
        result += 4;
        //string[8]      auth-plugin-data-part-1
        result += 8;
        //1              [00] filler
        result += 1;
        //2              capability flags (lower 2 bytes)
        result += 2;
        
        //1              character set
        result += 1;
        //2              status flags
        result += 2;
        //2              capability flags (upper 2 bytes)
        result += 2;
        
        if (capabilities != null && capabilities.isClientPluginAuth()) {
            //1              length of auth-plugin-data
            result += 1;
        } else {
            //1              [00] filler
            result += 1;
        }
        
        //string[10]     reserved (all [00])
        result += 10;
        
        if (capabilities.isClientSecureConnection()) {
            // string[$len]   auth-plugin-data-part-2 ($len=MAX(13, length of auth-plugin-data - 8))
            result += 13;
        }
        
        if (capabilities.isClientPluginAuth()) {
            // string[NUL]    auth-plugin name
            result += MysqlByteBufUtil.getNullTerminatedStringLength(getAuthPluginName().getBytes());
        }
        
        return result;
    }    
}
