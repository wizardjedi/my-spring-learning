package com.comp.bplustree;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilsTest {
    @Test
    public void testRemoveFromArray() {
        Long[] initialArray = new Long[]{1L,2L,3L,4L};
        
        Assert.assertNull(ArrayUtils.removeFromArray(null, 0));
        Assert.assertNull(ArrayUtils.removeFromArray(new Long[]{1L}, 0));
        
        Assert.assertArrayEquals(new Long[]{2L,3L,4L}, ArrayUtils.removeFromArray(initialArray, 0));
        Assert.assertArrayEquals(new Long[]{1L,2L,3L}, ArrayUtils.removeFromArray(initialArray, 3));
        
        Assert.assertArrayEquals(new Long[]{1L,3L,4L}, ArrayUtils.removeFromArray(initialArray, 1));
        Assert.assertArrayEquals(new Long[]{1L,2L,4L}, ArrayUtils.removeFromArray(initialArray, 2));
    }
    
    @Test
    public void testBinarySearch() {
        Long[] array = {1L, 2L, 4L};
        
        System.out.println("i="+Arrays.binarySearch(array, 0, array.length, 2L));
        System.out.println("i="+Arrays.binarySearch(array, 0, array.length, 3L));
        System.out.println("i="+Arrays.binarySearch(array, 0, array.length, 5L));
    }
    
}
