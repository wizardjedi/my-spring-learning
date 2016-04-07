import com.lrn.nettymysqlprotocol.HexUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class HexUtilsTest {
    @Test
    public void testValid() throws Exception {
        HexUtils.checkHexString(null);
        HexUtils.checkHexString("");
        HexUtils.checkHexString("0123456789AbCdEf");
    }
    
    @Test(expected = Exception.class)
    public void testInvalidLength() throws Exception {
        HexUtils.checkHexString("012");
    }
    
    @Test(expected = Exception.class)
    public void testInvalidStringSymbols() throws Exception {
        HexUtils.checkHexString(" FakeString");
    }
    
    @Test
    public void testCharToShort() {
        assertEquals(0, HexUtils.charToShort('0'));
        assertEquals(1, HexUtils.charToShort('1'));
        assertEquals(2, HexUtils.charToShort('2'));
        assertEquals(3, HexUtils.charToShort('3'));
        assertEquals(4, HexUtils.charToShort('4'));
        assertEquals(5, HexUtils.charToShort('5'));
        assertEquals(6, HexUtils.charToShort('6'));
        assertEquals(7, HexUtils.charToShort('7'));
        assertEquals(8, HexUtils.charToShort('8'));
        assertEquals(9, HexUtils.charToShort('9'));
        assertEquals(10, HexUtils.charToShort('a'));
        assertEquals(10, HexUtils.charToShort('A'));
        assertEquals(11, HexUtils.charToShort('b'));
        assertEquals(11, HexUtils.charToShort('B'));
        assertEquals(12, HexUtils.charToShort('c'));
        assertEquals(12, HexUtils.charToShort('C'));
        assertEquals(13, HexUtils.charToShort('d'));
        assertEquals(13, HexUtils.charToShort('D'));
        assertEquals(14, HexUtils.charToShort('e'));
        assertEquals(14, HexUtils.charToShort('E'));
        assertEquals(15, HexUtils.charToShort('f'));
        assertEquals(15, HexUtils.charToShort('F'));
    }

    @Test
    public void testShortToChar() {
        assertEquals('0', HexUtils.shortToChar((short)0));
        assertEquals('1', HexUtils.shortToChar((short)1));
        assertEquals('2', HexUtils.shortToChar((short)2));
        assertEquals('3', HexUtils.shortToChar((short)3));
        assertEquals('4', HexUtils.shortToChar((short)4));
        assertEquals('5', HexUtils.shortToChar((short)5));
        assertEquals('6', HexUtils.shortToChar((short)6));
        assertEquals('7', HexUtils.shortToChar((short)7));
        assertEquals('8', HexUtils.shortToChar((short)8));
        assertEquals('9', HexUtils.shortToChar((short)9));
        assertEquals('a', HexUtils.shortToChar((short)0x0A));
        assertEquals('b', HexUtils.shortToChar((short)0x0B));
        assertEquals('c', HexUtils.shortToChar((short)0x0C));
        assertEquals('d', HexUtils.shortToChar((short)0x0D));
        assertEquals('e', HexUtils.shortToChar((short)0x0E));
        assertEquals('f', HexUtils.shortToChar((short)0x0F));
    }
    
    @Test(expected = RuntimeException.class)
    public void testInvalidCharInCharToShort() {
        HexUtils.charToShort('_');
    }
    
    @Test(expected = RuntimeException.class)
    public void testInvalidByteInShortToChar() {
        HexUtils.shortToChar((short)16);
    }
    
    @Test
    public void testByteToHex() {
        assertEquals("0120feab",HexUtils.byteToHex(new byte[]{0x01,0x20,(byte)0xfe,(byte)0xab}));
    }
    
    @Test
    public void testByteToHexEmpty() {
        assertEquals("",HexUtils.byteToHex(new byte[]{}));
    }
    
    @Test
    public void testByteToHexNull() {
        assertEquals("",HexUtils.byteToHex(null));
    }

    @Test
    public void testRenderBit() {
        assertEquals("1.. | First bit of 3", HexUtils.renderBit(1, 3, 0, "First bit of 3"));
        assertEquals("0.. | First bit of 3", HexUtils.renderBit(0, 3, 0, "First bit of 3"));
        
        assertEquals(".1. | Second bit of 3", HexUtils.renderBit(1, 3, 1, "Second bit of 3"));
        assertEquals(".0. | Second bit of 3", HexUtils.renderBit(0, 3, 1, "Second bit of 3"));
        
        assertEquals("..1 | Last bit of 3", HexUtils.renderBit(1, 3, 2, "Last bit of 3"));
        assertEquals("..0 | Last bit of 3", HexUtils.renderBit(0, 3, 2, "Last bit of 3"));
    }
    
    @Test
    public void testValidConversion() throws Exception {
        assertArrayEquals(new byte[]{0x01}, HexUtils.hexToByte("01"));
        assertArrayEquals(new byte[]{0x23, 0x45}, HexUtils.hexToByte("2345"));
        assertArrayEquals(new byte[]{0x23, 0x45, (byte)0xab,(byte)0xcd,(byte)0xef}, HexUtils.hexToByte("2345aBcDeF"));
        assertArrayEquals(new byte[]{0x23, 0x45, (byte)0xab,(byte)0xcd,(byte)0xef}, HexUtils.hexToByte("2345AbCdEf"));
    }
}
