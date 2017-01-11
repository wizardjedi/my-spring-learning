package lrn.operation;

import org.junit.Test;
import static org.junit.Assert.*;

public class AndBlockTest {
    @Test
    public void testTrue() {
        AndBlock andBlock = new AndBlock();
        
        andBlock.addOperation(new EqualOperation("1","1"));
        
        assertEquals(true, andBlock.getResult());
    }
    
    @Test
    public void testTrue3() {
        AndBlock andBlock = new AndBlock();
        
        andBlock.addOperation(new EqualOperation("1","1"));
        andBlock.addOperation(new EqualOperation("1","1"));
        andBlock.addOperation(new EqualOperation("1","1"));
        
        assertEquals(true, andBlock.getResult());
    }
    
    @Test
    public void testFalse() {
        AndBlock andBlock = new AndBlock();
        
        andBlock.addOperation(new EqualOperation("1","2"));
        
        assertEquals(false, andBlock.getResult());
    }
    
    @Test
    public void testFalse3() {
        AndBlock andBlock = new AndBlock();
        
        andBlock.addOperation(new EqualOperation("1","1"));
        andBlock.addOperation(new EqualOperation("1","1"));
        andBlock.addOperation(new EqualOperation("1","2"));
        
        assertEquals(false, andBlock.getResult());
    }
    
}
