package com.lrn.nettymysqlprotocol;

public class HexUtils {
    public static byte[] hexToByte(String hex) throws Exception {
        if (hex == null || "".equals(hex)) {
            return new byte[]{};
        } else {
            checkHexString(hex);
            
            byte[] result = new byte[hex.length() / 2];

            short tmp;
            int j=0;
            for (int i=0;i<hex.length();i+=2) {
                tmp = (short) (charToShort(hex.charAt(i)) << 4);
                tmp |= charToShort(hex.charAt(i + 1));

                result[j] = (byte) tmp;

                j++;
            }

            return result;
        }
    }
    
    public static final short charToShort(char c) {
        if (c >= 0x30 && c <= 0x39) {
            return (short) (c - 0x30);
        } else {
            if (c >= 'a' && c<='f') {
                return (short) (c - 'a'+10);
            } else {
                if (c>='A' && c<='F') {
                    return (short) (c - 'A'+10);
                } else {
                    throw new RuntimeException("Conversion exception");
                }
            }
        }
    }

    
    public static void checkHexString(String hex) throws Exception {
        if (hex != null) {
            if ( (hex.length() % 2) != 0 ) {
                throw new Exception("Invalid length");
            }
        
            if (!"".equals(hex) && !hex.matches("^[0-9a-fA-F]+$")) {
                throw new Exception("Invalid string");
            }
        }
    }
}
