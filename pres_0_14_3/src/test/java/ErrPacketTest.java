import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.protocol.impl.ErrPacket;
import com.lrn.nettymysqlprotocol.protocol.impl.OkPacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ErrPacketTest {

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
    public void testErrPacket() throws Exception {
        ByteBuf buffer = Unpooled.buffer();

        ErrPacket errPacket = new ErrPacket();
        errPacket.setErrorCode(0x0448);
        errPacket.setSqlState("HY000");
        errPacket.setErrorMessage("No tables used");
        errPacket.setSequenceNumber(1);
        
        capabilities.setClientProtocol41();

        transcoder.encode(errPacket, buffer);

        assertEquals("17000001ff48042348593030304e6f207461626c65732075736564", ByteBufUtil.hexDump(buffer));
    }
}
