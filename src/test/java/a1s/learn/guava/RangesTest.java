/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a1s.learn.guava;

import com.google.common.collect.Range;
import junit.framework.TestCase;



public class RangesTest extends TestCase{
    public void testContains(){
        Range<Long> r = Range.closed(79100000000L, 79109999999L);
                
        assertTrue(r.contains(79101234567L));
    }
}
