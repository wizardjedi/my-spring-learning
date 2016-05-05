package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.Decoder;
import com.lrn.nettymysqlprotocol.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author wiz
 */
public class ComQueryPacketTest {
    
    @Test
    public void testRead() {
        String queryStr = "210000000373656c65637420404076657273696f6e5f636f6d6d656e74206c696d69742031";
        
        byte[] byteQuery = HexUtil.toByteArray(queryStr);
        
        ByteBuf writeByteBuffer = Unpooled.buffer().writeBytes(byteQuery);
        
        Decoder decoder = new Decoder();
        
        ComQueryPacket cqp = (ComQueryPacket) decoder.decode(writeByteBuffer);
        
        Assert.assertEquals("select @@version_comment limit 1", cqp.getQuery());
    }
    
}
