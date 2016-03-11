package com.lrn.nettymysqlprotocol;

import io.netty.buffer.ByteBuf;

public class MysqlGreeting {


    public static ByteBuf greeting(ByteBuf byteBuf) {

        String greet =
             "5b0000000a352e362e32382d30756275"
            +"6e7475302e31352e30342e31000b0000"
            +"006527315e686e716b00fff70802007f"
            +"80150000000000000000000048633c40"
            +"6a783d635d29513e006d7973716c5f6e"
            +"61746976655f70617373776f726400";

        byte[] toByteArray = HexUtil.toByteArray(greet);

        byteBuf.writeBytes(toByteArray);

        return byteBuf;
    }
}
