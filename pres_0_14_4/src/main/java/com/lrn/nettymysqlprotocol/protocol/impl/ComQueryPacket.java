package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/com-query.html
 */
public class ComQueryPacket extends BasePacket {

    protected String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        int len = getBodyLength() - 1;

        setQuery(new String(MysqlByteBufUtil.readEofString(bb, len), "UTF-8"));
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        MysqlByteBufUtil.writeInt1(bb, MysqlConstants.CommandConstants.COM_QUERY);
        MysqlByteBufUtil.writeStringEof(bb, getQuery().getBytes());
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        int result = 1;

        result+= MysqlByteBufUtil.getStringEofLength(getQuery().getBytes());

        return result;
    }

    @Override
    public String toString() {
        return "ComQueryPacket{" + "query=" + query + '}';
    }
}
