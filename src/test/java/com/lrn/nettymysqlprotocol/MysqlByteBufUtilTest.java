/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrn.nettymysqlprotocol;

import com.lrn.nettymysqlprotocol.protocol.ServerCapabilitiesEnum;
import org.junit.Test;

public class MysqlByteBufUtilTest {

    @Test
    public void longToCaps() {
        ServerCapabilitiesEnum[] longToServerCapabilities = MysqlByteBufUtil.longToServerCapabilities(0x7fff);

        for (int i=0;i<longToServerCapabilities.length;i++) {
            System.out.println(longToServerCapabilities[i]);
        }
    }

}
