package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.ComQueryPacket;
import com.lrn.nettymysqlprotocol.protocol.EofCommand;
import com.lrn.nettymysqlprotocol.protocol.OkCommand;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProcessor extends ChannelInboundHandlerAdapter {
  public static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        logger.debug("Read {}", msg);
        
        if (msg instanceof ComQueryPacket) {
            ComQueryPacket query = (ComQueryPacket)msg;
            
            logger.debug("On query {}", query.getQuery());
            
            BasePacket bp = onQuery(query.getQuery());
            
            ctx.channel().writeAndFlush(bp);
        }
        
        if (msg instanceof ComSleepPacket) {
            ctx.channel().writeAndFlush(new OkCommand());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        logger.debug("Exception", cause);

        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }  

    protected BasePacket onQuery(String query) {
        List<String> fields = new ArrayList<String>();
        
        fields.add("Some field1");
        fields.add("Some field2");
        
        List<List<String>> resultValues = new ArrayList<>();
        
        for (int i=0;i<10;i++) {
            List<String> values = new ArrayList<>();

            values.add("Значение поля 1 строки "+i);
            values.add((new Date()).toString());
        
            resultValues.add(values);
        }
        
        ResultSetPacket rsp = new ResultSetPacket();
        rsp.setFields(fields);
        rsp.setValues(resultValues);
        
        return rsp;
    }
}
