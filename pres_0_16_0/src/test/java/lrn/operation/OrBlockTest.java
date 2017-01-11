package lrn.operation;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrBlockTest {
    @Test
    public void testTrue() {
        OrBlock orBlock = new OrBlock();
        
        orBlock.addOperation(new EqualOperation("1","1"));
        
        assertEquals(true, orBlock.getResult());
    }
    
    @Test
    public void testTrue3() {
        OrBlock orBlock = new OrBlock();
        
        orBlock.addOperation(new EqualOperation("1","2"));
        orBlock.addOperation(new EqualOperation("1","1"));
        orBlock.addOperation(new EqualOperation("1","2"));
        
        assertEquals(true, orBlock.getResult());
    }
    
    @Test
    public void testFalse() {
        OrBlock orBlock = new OrBlock();
        
        orBlock.addOperation(new EqualOperation("1","2"));
        
        assertEquals(false, orBlock.getResult());
    }
    
    @Test
    public void testFalse3() {
        OrBlock orBlock = new OrBlock();
        
        orBlock.addOperation(new EqualOperation("1","2"));
        orBlock.addOperation(new EqualOperation("1","2"));
        orBlock.addOperation(new EqualOperation("1","2"));
        
        assertEquals(false, orBlock.getResult());
    }
    
}
