import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.protocol.impl.InitialHandshakePacket;
import com.lrn.nettymysqlprotocol.protocol.impl.OkPacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InitialHandshakePacketTest {
    
    protected MysqlTranscoder transcoder;
    
    protected TranscoderContext transcoderContext;
    
    protected Capabilities capabilities;
    
    protected ServerStatus serverStatus;
    
    public static final String FAKE_MY_SQL_SERVER_VERSION = "5.6.28-0ubuntu0.15.04.1";
    
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
        InitialHandshakePacket initialHandshake = new InitialHandshakePacket();

        capabilities.setCapabilities(0x807ff7ff);
        serverStatus.setStatus(0x0002);
        
        initialHandshake.setSequenceNumber(0);
        
        
        initialHandshake.setProtocolVersion(10);
        initialHandshake.setConnectionId(11);
        initialHandshake.setServerName(FAKE_MY_SQL_SERVER_VERSION);
        initialHandshake.setAuthPluginName("mysql_native_password");
        initialHandshake.setScramble("e'1^hnqkHc<@jx=c])Q>".getBytes());
        initialHandshake.setCharacterSet(MysqlConstants.CharsetConstants.LATIN1_SWEDISH_CI);
        initialHandshake.setCapabilities(capabilities);
        initialHandshake.setStatus(serverStatus);

        ByteBuf buffer = Unpooled.buffer();

        transcoder.encode(initialHandshake, buffer);

        String expected =
             "5b0000000a352e362e32382d30756275"
            +"6e7475302e31352e30342e31000b0000"
            +"006527315e686e716b00fff70802007f"
            +"80150000000000000000000048633c40"
            +"6a783d635d29513e006d7973716c5f6e"
            +"61746976655f70617373776f726400";

        String hexBuf = ByteBufUtil.hexDump(buffer);
        
        assertEquals(expected.toLowerCase(), hexBuf.toLowerCase());
    }
}
