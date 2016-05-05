package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.MysqlByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;

public class InitialHandshakeTest {
    public static final String FAKE_MY_SQL_SERVER_VERSION = "5.6.28-0ubuntu0.15.04.1";

    @Test
    public void testServerVersion() {
        InitialHandshake initialHandshake = new InitialHandshake();

        initialHandshake.setProtocolVersion(10);
        initialHandshake.setConnectionId(11);
        initialHandshake.setServerVersion(FAKE_MY_SQL_SERVER_VERSION);
        initialHandshake.setAuthPluginName("mysql_native_password");
        initialHandshake.setAuthPluginData("e'1^hnqkHc<@jx=c])Q>");
        initialHandshake.setCharacterSet(CharacterSetEnum.latin1_swedish_ci);
        initialHandshake.setServerCapabilities(MysqlByteBufUtil.longToServerCapabilities(0x807ff7ff));
        initialHandshake.setServerStatus(MysqlByteBufUtil.longToServerStatus(0x0002));

        ByteBuf buffer = Unpooled.buffer();

        initialHandshake.writePacket(buffer);

         String expected =
             "5b0000000a352e362e32382d30756275"
            +"6e7475302e31352e30342e31000b0000"
            +"006527315e686e716b00fff70802007f"
            +"80150000000000000000000048633c40"
            +"6a783d635d29513e006d7973716c5f6e"
            +"61746976655f70617373776f726400";

        String hexBuf = ByteBufUtil.hexDump(buffer);

        System.out.println(hexBuf);

        Assert.assertEquals(expected.toLowerCase(), hexBuf.toLowerCase());
    }


}
