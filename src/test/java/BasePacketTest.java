import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;

public class BasePacketTest {
    public class EmptyPacket extends BasePacket {
        protected long emptyPacketLength;

        public long getEmptyPacketLength() {
            return emptyPacketLength;
        }

        public void setEmptyPacketLength(long emptyPacketLength) {
            this.emptyPacketLength = emptyPacketLength;
        }

        @Override
        public long getPacketLength() {
            return this.getEmptyPacketLength();
        }

        @Override
        public ByteBuf encodePacket(ByteBuf byteBuf) {
            /* Nothing to do */
            return byteBuf;
        }
    }

    @Test
    public void testEmpty() {
        ByteBuf buffer = Unpooled.buffer();

        EmptyPacket packet = new EmptyPacket();

        packet.writePacket(buffer);

        Assert.assertEquals("00000000", ByteBufUtil.hexDump(buffer));
    }

    @Test
    public void testSequenceNumber() {
        ByteBuf buffer = Unpooled.buffer();

        EmptyPacket packet = new EmptyPacket();
        packet.setPacketNumber(0x45);

        packet.writePacket(buffer);

        Assert.assertEquals("00000045", ByteBufUtil.hexDump(buffer));
    }

    @Test
    public void testFakeLength() {
        ByteBuf buffer = Unpooled.buffer();

        EmptyPacket packet = new EmptyPacket();
        packet.setPacketNumber(0x45);
        packet.setEmptyPacketLength(0x56);

        packet.writePacket(buffer);

        Assert.assertEquals("56000045", ByteBufUtil.hexDump(buffer));
    }
}
