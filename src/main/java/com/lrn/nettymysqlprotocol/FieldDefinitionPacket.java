package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.CharacterSetEnum;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class FieldDefinitionPacket extends BasePacket {
    protected String catalog = "def";
    protected String schema = "";
    protected String table = "";
    protected String org_table = "";
    protected String name = "";
    protected String org_name = "";
    protected long lengthOfFixedFields = 0x0c;
    protected CharacterSetEnum charset = CharacterSetEnum.utf8_general_ci;
    protected long columnLength = 10*1000;
    protected long type = 0xfd;
    protected long flags = 0;
    protected long decimals = 0x1f;

    @Override
    public long getPacketLength() {
        return
            MysqlByteBufUtil.getLenencStringLength(getCatalog().getBytes()) + 
            MysqlByteBufUtil.getLenencStringLength(getSchema().getBytes()) + 
            MysqlByteBufUtil.getLenencStringLength(getTable().getBytes()) +                 
            MysqlByteBufUtil.getLenencStringLength(getOrg_table().getBytes()) +                 
            MysqlByteBufUtil.getLenencStringLength(getName().getBytes()) +                 
            MysqlByteBufUtil.getLenencStringLength(getOrg_name().getBytes()) +             
            MysqlByteBufUtil.getLenencIntegerLength(getLengthOfFixedFields()) +             
            2 +
            4 +
            1 +
            2 +
            1 +
            2;
    }

    @Override
    public ByteBuf encodePacket(ByteBuf byteBuf) {
        MysqlByteBufUtil.writeLenencString(byteBuf, getCatalog().getBytes());
        MysqlByteBufUtil.writeLenencString(byteBuf, getSchema().getBytes());
        MysqlByteBufUtil.writeLenencString(byteBuf, getTable().getBytes());
        MysqlByteBufUtil.writeLenencString(byteBuf, getOrg_table().getBytes());
        MysqlByteBufUtil.writeLenencString(byteBuf, getName().getBytes());
        MysqlByteBufUtil.writeLenencString(byteBuf, getOrg_name().getBytes());
        MysqlByteBufUtil.writeLenencInteger(byteBuf, getLengthOfFixedFields());

        MysqlByteBufUtil.writeInt2(byteBuf, getCharset().getValue());
        MysqlByteBufUtil.writeInt4(byteBuf, getColumnLength());
        MysqlByteBufUtil.writeInt1(byteBuf, getType());
        MysqlByteBufUtil.writeInt2(byteBuf, getFlags());
        MysqlByteBufUtil.writeInt1(byteBuf, getDecimals());
        MysqlByteBufUtil.writeInt2(byteBuf, 0x00);
        
        return byteBuf;
    }

    @Override
    public ByteBuf writePacket(ByteBuf byteBuf) {
        super.writePacket(byteBuf);

        byteBuf.writeBytes(encodePacket(Unpooled.buffer()));

        return byteBuf;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getOrg_table() {
        return org_table;
    }

    public void setOrg_table(String org_table) {
        this.org_table = org_table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public long getLengthOfFixedFields() {
        return lengthOfFixedFields;
    }

    public void setLengthOfFixedFields(long lengthOfFixedFields) {
        this.lengthOfFixedFields = lengthOfFixedFields;
    }

    public CharacterSetEnum getCharset() {
        return charset;
    }

    public void setCharset(CharacterSetEnum charset) {
        this.charset = charset;
    }

    public long getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(long columnLength) {
        this.columnLength = columnLength;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getFlags() {
        return flags;
    }

    public void setFlags(long flags) {
        this.flags = flags;
    }

    public long getDecimals() {
        return decimals;
    }

    public void setDecimals(long decimals) {
        this.decimals = decimals;
    }
    
    
    
}
