package com.comp.bplustree;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BTreeTest {
    protected BTree<Long, Long> tree = null;
    
    @Before
    public void setUp() {
        tree = new BTree<Long, Long>();
    }
    
    @Test
    public void testInsert() throws IOException {
        tree.insert(1L, 1L);
       
        Assert.assertNotNull(tree.getRoot());
        
        Assert.assertArrayEquals(new Long[]{1L}, tree.getRoot().getKeys());
        Assert.assertArrayEquals(new Long[]{1L}, tree.getRoot().getValues());
        
        tree.insert(3L, 3L);
        tree.insert(2L, 2L);
        tree.insert(4L, 4L);
        
        Assert.assertArrayEquals(new Long[]{1L, 2L, 3L, 4L}, tree.getRoot().getKeys());
        Assert.assertArrayEquals(new Long[]{1L, 2L, 3L, 4L}, tree.getRoot().getValues());

        tree.insert(5L, 5L);        
        
        Assert.assertArrayEquals(new Long[]{3L}, tree.getRoot().getKeys());
        Assert.assertArrayEquals(new Long[]{1L,2L,3L}, tree.getRoot().getPointers()[0].getKeys());
        Assert.assertArrayEquals(new Long[]{1L,2L,3L}, tree.getRoot().getPointers()[0].getValues());
        
        Assert.assertArrayEquals(new Long[]{4L,5L}, tree.getRoot().getPointers()[1].getKeys());
        Assert.assertArrayEquals(new Long[]{4L,5L}, tree.getRoot().getPointers()[1].getValues());          
    }
    
    @Test
    public void testInsert2() throws IOException {
        //BTreeNode.buildGraphviz(tree.getRoot(), "/tmp/0.dot");
        
        for (int i=1;i<100;i++) {
            tree.insert(Long.valueOf(i), Long.valueOf(i));
            
            BTreeNode.buildGraphviz(tree.getRoot(), "/tmp/"+i+".dot");
        }
    }
    
    @Test
    public void testDelete() {
        tree.insert(0L, 0L);
        tree.insert(1L, 1L);
        tree.insert(2L, 2L);
        
        Assert.assertTrue(tree.ckeckExists(0L));
        Assert.assertTrue(tree.ckeckExists(1L));
        Assert.assertTrue(tree.ckeckExists(2L));
        
        tree.delete(1L);
        
        Assert.assertTrue(tree.ckeckExists(0L));
        Assert.assertFalse(tree.ckeckExists(1L));
        Assert.assertTrue(tree.ckeckExists(2L));
    }
    
    @Test
    public void testDelete2() throws IOException {
        tree.insert(1L, 1L);        
        tree.insert(2L, 2L);
        tree.insert(3L, 3L);        
        tree.insert(4L, 4L);
        tree.insert(5L, 5L);        
        
        BTreeNode.buildGraphviz(tree.getRoot(), "/tmp/before-delete.dot");
        
        tree.delete(1L);
        
        BTreeNode.buildGraphviz(tree.getRoot(), "/tmp/delete.dot");
        
        Assert.assertFalse(tree.ckeckExists(1L));
        Assert.assertTrue(tree.ckeckExists(2L));
        Assert.assertTrue(tree.ckeckExists(3L));
        Assert.assertTrue(tree.ckeckExists(4L));
        Assert.assertTrue(tree.ckeckExists(5L));
    }
    
    @Test
    public void testInsert3() throws IOException {
        for (int i=1;i<10;i++) {
            tree.insert(Long.valueOf(i), Long.valueOf(i));
        }
        
        try {
            tree.insert(3L, 3L);
            tree.insert(3L, 3L);
            tree.insert(3L, 3L);
            tree.insert(3L, 3L);
            tree.insert(3L, 3L);
            tree.insert(3L, 3L);
            tree.insert(3L, 3L);
            tree.insert(3L, 3L);
        } catch (BTree.DuplicateKeyException e) {
            /* */
        }
        BTreeNode.buildGraphviz(tree.getRoot(), "/tmp/dup.dot");
    }
}
