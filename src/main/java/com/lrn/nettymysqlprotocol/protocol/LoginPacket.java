package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.MysqlByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class LoginPacket extends BasePacket {

    protected String login;
    
    protected String password;
    
    protected CharacterSetEnum charset;
    
    protected ServerCapabilitiesEnum[] capabilities;

    protected long maxPacket;

    public long getMaxPacket() {
        return maxPacket;
    }

    public void setMaxPacket(long maxPacket) {
        this.maxPacket = maxPacket;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CharacterSetEnum getCharset() {
        return charset;
    }

    public void setCharset(CharacterSetEnum charset) {
        this.charset = charset;
    }

    public ServerCapabilitiesEnum[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ServerCapabilitiesEnum[] capabilities) {
        this.capabilities = capabilities;
    }
    
    @Override
    public long getPacketLength() {
        long length = 0;
        
        
        
        return length;
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        return null;
    }
    
    @Override
    public void readBody(ByteBuf bb) throws Exception {
        long capabilities = MysqlByteBufUtil.readInt4(bb);
        long maxPacket = MysqlByteBufUtil.readInt4(bb);
        int charset = bb.readUnsignedByte();
        
        bb.readBytes(23); // reserver 23 0x00
        byte[] loginBytes = MysqlByteBufUtil.readNullTerminatedString(bb);
        byte[] passwordBytes = MysqlByteBufUtil.readNullTerminatedString(bb);
        
        this.setMaxPacket(maxPacket);
        this.setCharset(MysqlByteBufUtil.byteToCharset(charset));
        this.setCapabilities(MysqlByteBufUtil.longToServerCapabilities(capabilities));
        this.setLogin(new String(loginBytes, "UTF-8"));
        this.setPassword(new String(passwordBytes, "UTF-8"));
    }
    
    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        super.writePacket(byteBuf);

        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        return byteBuf;
    }
}
