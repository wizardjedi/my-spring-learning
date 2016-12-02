import com.lrn.nettymysqlprotocol.HexUtils;
import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.protocol.impl.ComQueryPacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ComQueryPacketTest {

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
    public void testRead() throws Exception {
        String queryStr = "210000000373656c65637420404076657273696f6e5f636f6d6d656e74206c696d69742031";

        byte[] byteQuery = HexUtils.hexToByte(queryStr);

        ByteBuf writeByteBuffer = Unpooled.buffer().writeBytes(byteQuery);

        ComQueryPacket cqp = (ComQueryPacket) transcoder.decode(writeByteBuffer);

        Assert.assertEquals("select @@version_comment limit 1", cqp.getQuery());
    }

    @Test
    public void testWrite() throws Exception {
        ComQueryPacket comQueryPacket = new ComQueryPacket();
        comQueryPacket.setQuery("select @@version_comment limit 1");

        ByteBuf buffer = Unpooled.buffer();

        transcoder.encode(comQueryPacket, buffer);

        String expected = "210000000373656c65637420404076657273696f6e5f636f6d6d656e74206c696d69742031";

        String hexBuf = ByteBufUtil.hexDump(buffer);

        assertEquals(expected.toLowerCase(), hexBuf.toLowerCase());
    }
}
