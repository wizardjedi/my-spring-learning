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
    
    public static final String byteToHex(byte[] b) {
        if (b == null || b.length == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i=0;i<b.length;i++) {
            short tmp = (short)b[i];
            
            sb.append(shortToChar((short)((tmp >> 4) & 0x0F)));
            sb.append(shortToChar((short)(tmp & 0x0F)));
        }
        
        return sb.toString();
    }
    
    public static final String renderBit(boolean value, long length, long num, String description) {
        return renderBit(value ? 1 : 0, length, num, description);
    }
    
    public static final String renderBit(int value, long length, long num, String description) {
        StringBuilder sb = new StringBuilder();
        
        for (long i = (length-1);i >= 0;i--) {
            if (i == num) {
                sb.append(value);
            } else {
                sb.append(".");
            }
            
            if (i % 4 == 0) {
                sb.append(" ");
            }
            
            if (i % 8 == 0) {
                sb.append("| ");
            }
        }        
                    
        sb.append(description);
        
        return sb.toString();
    }
    
    public static final char shortToChar(short c) {
        if (c>=0 && c<=9) {
            return (char)('0'+c);
        } else {
            if (c>=10 && c<=15) {
                return (char)('a'+(c-10));
            } else {
                throw new RuntimeException("Conversion exception");
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
