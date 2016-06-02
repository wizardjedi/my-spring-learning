import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import org.junit.Before;

public class EofPacketTest {

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


}
