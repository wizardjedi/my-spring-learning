package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.server.ServerObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerObjectToPacketEncoder extends MessageToMessageEncoder<ServerObject>  {
    public static final Logger logger = LoggerFactory.getLogger(ServerObjectToPacketEncoder.class);
    
    public MysqlTranscoder transcoder = new MysqlTranscoder();

    @Override
    protected void encode(ChannelHandlerContext chc, ServerObject serverObject, List<Object> list) throws Exception {
        /**
         * @TODO: convert server object to list of mysql packets
         */
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
