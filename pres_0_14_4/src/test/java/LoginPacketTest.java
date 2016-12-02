import com.lrn.nettymysqlprotocol.HexUtils;
import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.MysqlConstants;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.protocol.impl.LoginPacket;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.Unpooled;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginPacketTest {

    protected MysqlTranscoder transcoder;

    protected TranscoderContext transcoderContext;

    protected Capabilities capabilities;

    protected ServerStatus serverStatus;

    @Before
    public void before() {
        transcoder = new MysqlTranscoder();
        transcoderContext = new TranscoderContext();

        transcoderContext.setAuthPhase();

        transcoder.setContext(transcoderContext);

        capabilities = transcoderContext.getCapabilities();
        serverStatus = transcoderContext.getServerStatus();
    }

    @Test
    public void test1() throws Exception {
        String initialPacket =
            "ae00000105a67f00000000012100000000" +
            "0000000000000000000000000000000000" +
            "0000726f6f7400006d7973716c5f6e6174" +
            "6976655f70617373776f72640071035f6f" +
            "731064656269616e2d6c696e75782d676e" +
            "750c5f636c69656e745f6e616d65086c69" +
            "626d7973716c045f706964053237383138" +
            "0f5f636c69656e745f76657273696f6e06" +
            "352e362e3238095f706c6174666f726d06" +
            "7838365f36340c70726f6772616d5f6e61" +
            "6d65056d7973716c";

        byte[] initialByteArray = HexUtils.hexToByte(initialPacket);

        LoginPacket decodeLogin = (LoginPacket)transcoder.decode(Unpooled.buffer().writeBytes(initialByteArray));

        System.out.println(decodeLogin);

        Assert.assertEquals(174, decodeLogin.getBodyLength());
        Assert.assertEquals(1, decodeLogin.getSequenceNumber());

        Assert.assertEquals(MysqlConstants.CharsetConstants.UTF8_GENERAL_CI, decodeLogin.getCharset());
        Assert.assertEquals(16777216, decodeLogin.getMaxPacket());
        Assert.assertEquals("root", decodeLogin.getLogin());
        Assert.assertEquals("", decodeLogin.getPassword());
    }

}
