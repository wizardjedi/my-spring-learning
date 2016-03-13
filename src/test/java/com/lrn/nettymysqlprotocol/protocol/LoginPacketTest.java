/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.ByteToMysqlLoginDecoder;
import com.lrn.nettymysqlprotocol.Decoder;
import com.lrn.nettymysqlprotocol.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginPacketTest {
    
    @Test
    public void test1() {
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
        
        Decoder d = new Decoder();
        
        byte[] initialByteArray = HexUtil.toByteArray(initialPacket);
        
        LoginPacket decodeLogin = d.decodeLogin(Unpooled.buffer().writeBytes(initialByteArray));
        
        Assert.assertEquals(174, decodeLogin.getLength());
        Assert.assertEquals(1, decodeLogin.getPacketNumber());
        
        Assert.assertEquals(CharacterSetEnum.utf8_general_ci, decodeLogin.getCharset());
        Assert.assertEquals(16777216, decodeLogin.getMaxPacket());
        Assert.assertEquals("root", decodeLogin.getLogin());
        Assert.assertEquals("", decodeLogin.getPassword());
    }
    
}
