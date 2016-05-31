package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlPacketToByteEncoder extends MessageToByteEncoder<Packet> {

    public static final Logger logger = LoggerFactory.getLogger(MysqlPacketToByteEncoder.class);
    
    protected MysqlTranscoder transcoder = new MysqlTranscoder();

    public MysqlPacketToByteEncoder(MysqlTranscoder transcoder) {
        this.transcoder = transcoder;
    }
    
    public MysqlTranscoder getTranscoder() {
        return transcoder;
    }

    public void setTranscoder(MysqlTranscoder transcoder) {
        this.transcoder = transcoder;
    }
    
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        if (msg instanceof Packet) {
            transcoder.encode((Packet)msg, out);
        }
    }
}
