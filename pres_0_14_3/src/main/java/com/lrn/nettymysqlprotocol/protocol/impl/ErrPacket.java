package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 *
 * @see https://dev.mysql.com/doc/internals/en/packet-ERR_Packet.html
 */
public class ErrPacket extends BasePacket {
    protected long errorCode;
    protected String sqlState;
    protected String errorMessage;

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getSqlState() {
        return sqlState;
    }

    public void setSqlState(String sqlState) {
        this.sqlState = sqlState;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        MysqlByteBufUtil.writeInt1(bb, 0xff); // [ff] header of the ERR packet
        MysqlByteBufUtil.writeInt2(bb, getErrorCode()); // error_code 	error-code

        if (
            context != null
            && context.getCapabilities() != null
            && context.getCapabilities().isClientProtocol41()
        ) {
            MysqlByteBufUtil.writeString(bb, "#".getBytes());
            MysqlByteBufUtil.writeLengthedString(bb, getSqlState().getBytes(), 5);
        }

        MysqlByteBufUtil.writeStringEof(bb, getErrorMessage().getBytes());
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        int result =
                1       // header 	[ff] header of the ERR packet
                + 2;    //error_code 	error-code

        if (
            context != null
            && context.getCapabilities() != null
            && context.getCapabilities().isClientProtocol41()
        ) {
            result += 1; // sql_state_marker 	# marker of the SQL State
            result += 5; // sql_state 	SQL State
        }

        result += getErrorMessage().getBytes().length;

        return result;
    }
}
