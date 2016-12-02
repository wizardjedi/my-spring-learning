import com.lrn.nettymysqlprotocol.HexUtils;
import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.Packet;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.protocol.impl.ComQuitPacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ComQuitPacketTest {

    protected MysqlTranscoder transcoder;

    protected TranscoderContext transcoderContext;

    protected Capabilities capabilities;

    protected ServerStatus serverStatus;

    @Before
    public void before() {
        transcoder = new MysqlTranscoder();
        transcoderContext = new TranscoderContext();

        transcoderContext.setCommandPhase();

        transcoder.setContext(transcoderContext);

        capabilities = transcoderContext.getCapabilities();
        serverStatus = transcoderContext.getServerStatus();
    }

    @Test
    public void testWrite() throws Exception {
        ByteBuf buffer = Unpooled.buffer();

        ComQuitPacket comQuitPacket = new ComQuitPacket();
        comQuitPacket.setSequenceNumber(3);
        
        capabilities.setClientProtocol41();

        transcoder.encode(comQuitPacket, buffer);

        assertEquals("0100000301", ByteBufUtil.hexDump(buffer));
    }
    
    @Test
    public void testRead() throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        
        capabilities.setClientProtocol41();

        buffer.writeBytes(HexUtils.hexToByte("0100000301"));
        
        Packet packet = transcoder.decode(buffer);

        assertTrue(packet instanceof ComQuitPacket);
        
        assertEquals(3, ((ComQuitPacket)packet).getSequenceNumber());
    }
}
