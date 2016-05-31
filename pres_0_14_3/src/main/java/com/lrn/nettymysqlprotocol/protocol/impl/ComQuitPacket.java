package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/com-quit.html
 */
public class ComQuitPacket extends BasePacket {

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        /* */
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        MysqlByteBufUtil.writeInt1(bb, MysqlConstants.CommandConstants.COM_QUIT);        
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        return 1;
    }

    @Override
    public String toString() {
        return "ComQuitPacket{}";
    }
}
