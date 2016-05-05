import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.protocol.impl.OkPacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OkPacketTest {
    
    protected MysqlTranscoder transcoder;
    
    protected TranscoderContext transcoderContext;
    
    protected Capabilities capabilities;
    
    protected ServerStatus serverStatus;
    
    @Before
    public void before() {
        transcoder = new MysqlTranscoder();
        transcoderContext = new TranscoderContext();
        transcoder.setContext(transcoderContext);
        
        capabilities = transcoderContext.getCapabilities();
        serverStatus = transcoderContext.getServerStatus();
    }
    
    @Test
    public void testSimpleOk() throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        
        OkPacket okPacket = new OkPacket();
        okPacket.setSequenceNumber(2);
        
        capabilities.setClientProtocol41();
        serverStatus.setServerStatusAutocommit();
        
        transcoder.encode(okPacket, buffer);
        
        assertEquals("0700000200000002000000", ByteBufUtil.hexDump(buffer));
    }
    
     @Test
    public void testSimpleOk2() throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        
        OkPacket okPacket = new OkPacket();
        okPacket.setSequenceNumber(1);
        
        capabilities.setClientProtocol41();
        serverStatus.setServerStatusAutocommit();
        
        okPacket.setAffectedRows(1);
        okPacket.setLastInsertId(2);
        
        transcoder.encode(okPacket, buffer);
        
        assertEquals("0700000100010202000000", ByteBufUtil.hexDump(buffer));
    }
}
