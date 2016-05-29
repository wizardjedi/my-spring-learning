package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/com-query-response.html#packet-Protocol::ColumnDefinition
 */
public class ColumnDefinitionPacket extends BasePacket {
    protected String catalog = "def";
    protected String schema = "";
    protected String table = "";
    protected String org_table = "";
    protected String name = "";
    protected String org_name = "";
    protected long lengthOfFixedFields = 0x0c;
    protected long charset = MysqlConstants.CharsetConstants.UTF8_GENERAL_CI;
    protected long columnLength = 10*1000;
    protected long type = 0xfd;
    protected long flags = 0;
    protected long decimals = 0x1f;

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        MysqlByteBufUtil.writeLenencString(bb, getCatalog().getBytes());
        MysqlByteBufUtil.writeLenencString(bb, getSchema().getBytes());
        MysqlByteBufUtil.writeLenencString(bb, getTable().getBytes());
        MysqlByteBufUtil.writeLenencString(bb, getOrg_table().getBytes());
        MysqlByteBufUtil.writeLenencString(bb, getName().getBytes());
        MysqlByteBufUtil.writeLenencString(bb, getOrg_name().getBytes());
        MysqlByteBufUtil.writeLenencInteger(bb, getLengthOfFixedFields());

        MysqlByteBufUtil.writeInt2(bb, getCharset());
        MysqlByteBufUtil.writeInt4(bb, getColumnLength());
        MysqlByteBufUtil.writeInt1(bb, getType());
        MysqlByteBufUtil.writeInt2(bb, getFlags());
        MysqlByteBufUtil.writeInt1(bb, getDecimals());
        MysqlByteBufUtil.writeInt2(bb, 0x00);
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        return
            (int)(
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
            2);
    }

    public long getCharset() {
        return charset;
    }

    public void setCharset(long charset) {
        this.charset = charset;
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
