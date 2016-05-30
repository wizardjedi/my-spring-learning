package com.lrn.nettymysqlprotocol;

import static com.lrn.nettymysqlprotocol.App.logger;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.impl.InitialHandshakePacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultChannelInitializer extends ChannelInitializer<SocketChannel> {
    public static final Logger logger = LoggerFactory.getLogger(App.class);

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        logger.info("Client connected from:{}", ch.remoteAddress());

        ByteBuf greetingBuffer = ch.alloc().buffer();

        MysqlTranscoder transcoder = new MysqlTranscoder();

        transcoder.setContext(new TranscoderContext());

        transcoder.getContext().getCapabilities().setCapabilities(0x807ff7ff);
        transcoder.getContext().getServerStatus().setStatus(0x0002);

        InitialHandshakePacket greet = new InitialHandshakePacket();
        greet.setScramble("01234567890123456789".getBytes());
        greet.setCharacterSet(MysqlConstants.CharsetConstants.UTF8_GENERAL_CI);
        greet.setConnectionId(11);
        greet.setServerName("asdasd");
        greet.setSequenceNumber(0);

        transcoder.encode(greet, greetingBuffer);

        ch.writeAndFlush(greetingBuffer);

        ch.pipeline().addLast(new DefaultServerHandler(transcoder));
    }
}
