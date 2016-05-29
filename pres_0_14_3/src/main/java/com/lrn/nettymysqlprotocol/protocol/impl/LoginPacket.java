package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/connection-phase-packets.html#packet-Protocol::HandshakeResponse
 */
public class LoginPacket extends BasePacket {

    protected String login;

    protected String password;

    protected long charset;

    protected Capabilities capabilities;

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

    public long getCharset() {
        return charset;
    }

    public void setCharset(long charset) {
        this.charset = charset;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    /*@Override
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
        this.setCharset(this.charset);
        this.setCapabilities(this.capabilities);
        this.setLogin(new String(loginBytes, "UTF-8"));
        this.setPassword(new String(passwordBytes, "UTF-8"));
    }

    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        return byteBuf;
    }*/

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        long capabilities = MysqlByteBufUtil.readInt4(bb);
        long maxPacket = MysqlByteBufUtil.readInt4(bb);
        int charset = bb.readUnsignedByte();

        bb.readBytes(23); // reserver 23 0x00
        byte[] loginBytes = MysqlByteBufUtil.readNullTerminatedString(bb);
        byte[] passwordBytes = MysqlByteBufUtil.readNullTerminatedString(bb);

        this.setMaxPacket(maxPacket);
        this.setCharset(this.charset);
        this.setCapabilities(this.capabilities);
        this.setLogin(new String(loginBytes, "UTF-8"));
        this.setPassword(new String(passwordBytes, "UTF-8"));
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
