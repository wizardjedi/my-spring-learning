package com.comp.bplustree;

import java.lang.reflect.Array;
import java.util.Arrays;
import sun.security.util.KeyUtil;

public class ArrayUtils {    
    public static int findPosition(Comparable[] keys, Comparable key) {
        if (keys == null || keys.length == 0) {
            return -1;
        }
        
        int pos = Arrays.binarySearch(keys, key);
        
        return pos >= 0 ? pos : -1;
        
        /*for (int i=0;i<keys.length;i++) {
            if (keys[i].compareTo(key) == 0) {
                return i;
            }
        }*/
        
        //return -1;
    }
    
    public static int findLocation(Comparable[] keys, Comparable key) {
        if (keys == null || keys.length == 0) {
            return 0;
        }
        
        int pos = Arrays.binarySearch(keys, key);
        
        if (pos < 0) {
            return (-pos) - 1;
        } else {
            return pos;
        }
        /*Comparable prev = key;
        for (int i=0;i<keys.length;i++) {
        if (
        prev.compareTo(key) <=0
        && keys[i].compareTo(key) > 0
        ) {
        return i;
        }
        prev = keys[i];        
        }
        return keys.length;*/
    }
    
    public static <T> T[] mergeArrays(T[] left, T[] right) {
        /*if (array == null || array.length == 0) {
            return null;
        }*/
        
        T[] sub = (T [])Array.newInstance(array[0].getClass(), length);
        
        System.arraycopy(array, from, sub, 0, length);
        
        return sub;
    }
    
    public static <T> T[] subArray(T[] array, int from, int length) {
        if (array == null || array.length == 0) {
            return null;
        }
        
        T[] sub = (T [])Array.newInstance(array[0].getClass(), length);
        
        System.arraycopy(array, from, sub, 0, length);
        
        return sub;
    }
    
    public static <T> T[] removeFromArray(T[] array, int location) {
        if (array == null || array.length == 1) {
            return null;
        }
        
        T[] sub = (T [])Array.newInstance(array[0].getClass(), array.length - 1);
        
        if (location > 0) {
            System.arraycopy(array, 0, sub, 0, location);
        }
        
        if (location < array.length-1) {
            System.arraycopy(array, location+1, sub, location, array.length - location-1);
        }
        
        return sub;
    }
    
    public static <T> T[] insertToArray(T[] array, T obj, int location) {
        if (array == null || array.length == 0) {
            T[] sub = (T [])Array.newInstance(obj.getClass(), 1);
            sub[0] = obj;
            
            return sub;
        }
        
        T[] sub = (T [])Array.newInstance(obj.getClass(), array.length + 1);
        
        if (location - 1 >= 0) {
            System.arraycopy(array, 0, sub, 0, location );
        }

        sub[location] = obj;

        if (location < array.length) {
            System.arraycopy(array, location, sub, location + 1, array.length - location);
        }
        
        return sub;
    }
}
