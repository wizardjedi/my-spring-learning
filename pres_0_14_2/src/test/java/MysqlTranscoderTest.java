import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.transcoder.MysqlTranscoder;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;
import static org.junit.Assert.*;

public class MysqlTranscoderTest {
    protected MysqlTranscoder transcoder = new MysqlTranscoder();
    
    @Test
    public void test() throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        
        transcoder.setContext(new TranscoderContext());
        transcoder.encode(new EmptyClass(0x12), buffer);
        
        assertEquals("0100000012", ByteBufUtil.hexDump(buffer));
    }
    
    public class EmptyClass extends BasePacket {
        protected int val;

        public EmptyClass(int val) {
            this.val = val;
        }
        
        @Override
        public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
            this.val = (int)MysqlByteBufUtil.readInt1(bb);
        }

        @Override
        public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
            MysqlByteBufUtil.writeInt1(bb, val);
        }

        @Override
        public int calculateBodyLength(TranscoderContext context) {
            return MysqlByteBufUtil.getInt1Length();
        }
    }
}
