package com.comp.bplustree;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BtreeNodeTest {
    protected BTreeNode<Long, Long> tree = null;
    
    @Before
    public void setUp() {
        tree = new BTreeNode<Long, Long>();
    }
    
    @Test
    public void testSearchEmptyTree(){
        // empty tree
        assertEquals(null, tree.search(123L));
    }
    
    @Test
    public void testCheckMergeRight() {
        BTreeNode<Long, Long> left = BTreeNode.createLeaf(new Long[4], new Long[4]);
        BTreeNode<Long, Long> right = BTreeNode.createLeaf(new Long[2], new Long[2]);
        
        assertEquals(1, BTreeNode.checkMergeable(left, right));
    }
    
    @Test
    public void testCheckMergeLeft() {
        BTreeNode<Long, Long> left = BTreeNode.createLeaf(new Long[2], new Long[4]);
        BTreeNode<Long, Long> right = BTreeNode.createLeaf(new Long[4], new Long[4]);
        
        assertEquals(-1, BTreeNode.checkMergeable(left, right));
    }
    
    @Test
    public void testCheckNotMerge() {
        BTreeNode<Long, Long> left = BTreeNode.createLeaf(new Long[4], new Long[4]);
        BTreeNode<Long, Long> right = BTreeNode.createLeaf(new Long[4], new Long[4]);
        
        assertEquals(0, BTreeNode.checkMergeable(left, right));
    }
    
    @Test
    public void testSearchLegalValue(){
        Long[] keyValues = new Long[]{123L};
        
        tree.setKeys(keyValues);
        tree.setValues(keyValues);
        
        assertEquals(Long.valueOf(123), tree.search(123L));
    }
    
    @Test
    public void testSearchLegalValue2(){
        Long[] keyValues = new Long[]{123L, 247L};
        
        tree.setKeys(keyValues);
        tree.setValues(keyValues);
        
        assertEquals(Long.valueOf(123), tree.search(123L));
        assertEquals(Long.valueOf(247), tree.search(247L));
    }
    
    @Test
    public void testSearchIllegalValue(){
        Long[] keyValues = new Long[]{123L, 247L};
        
        tree.setKeys(keyValues);
        tree.setValues(keyValues);
        
        // empty tree
        assertEquals(null, tree.search(267L));
    }
    
    @Test
    public void testIndexTree() {
        // index leaf
        BTreeNode root = new BTreeNode<Long, Long>();
        root.setType(BTreeNodeType.DIRECTORY);
        Long[] kv1 = new Long[]{100L,200L};
        root.setKeys(kv1);
        root.setValues(kv1);
        
        BTreeNode s1 = new BTreeNode();
        Long[] kv2 = new Long[]{10L,50L,70L,90L};
        s1.setValues(kv2);
        s1.setKeys(kv2);
        
        BTreeNode s2 = new BTreeNode();
        Long[] kv3 = new Long[]{110L,150L,170L,190L};
        s2.setValues(kv3);
        s2.setKeys(kv3);
        
        BTreeNode s3 = new BTreeNode();
        Long[] kv4 = new Long[]{210L,250L,270L,290L};
        s3.setValues(kv4);
        s3.setKeys(kv4);
        
        root.setPointers(new BTreeNode[]{s1,s2,s3});
        
        // Exercise        
        assertEquals(Long.valueOf(10), root.search(10L));
        assertEquals(Long.valueOf(50), root.search(50L));
        assertEquals(Long.valueOf(70), root.search(70L));
        assertEquals(Long.valueOf(90), root.search(90L));
        
        assertEquals(Long.valueOf(110), root.search(110L));
        assertEquals(Long.valueOf(150), root.search(150L));
        assertEquals(Long.valueOf(170), root.search(170L));
        assertEquals(Long.valueOf(190), root.search(190L));
        
        assertEquals(Long.valueOf(210), root.search(210L));
        assertEquals(Long.valueOf(250), root.search(250L));
        assertEquals(Long.valueOf(270), root.search(270L));
        assertEquals(Long.valueOf(290), root.search(290L));
        
        assertEquals(null, root.search(291L));
        assertEquals(null, root.search(191L));
        assertEquals(null, root.search(91L));
        assertEquals(null, root.search(-91L));
        
        try {
            BTreeNode.buildGraphviz(root, "/tmp/1.dot");
        } catch (IOException e) {
            
        }
    }
    
    @Test
    public void testInsert() {
        tree.insert(3L, 3L, null);
        
        assertArrayEquals(new Long[]{3L}, tree.getKeys());
        assertArrayEquals(new Long[]{3L}, tree.getValues());
        
        tree.insert(1L, 1L, null);
        
        assertArrayEquals(new Long[]{1L, 3L}, tree.getKeys());
        assertArrayEquals(new Long[]{1L, 3L}, tree.getValues());
        
        tree.insert(2L, 2L, null);
        
        assertArrayEquals(new Long[]{1L, 2L, 3L}, tree.getKeys());
        assertArrayEquals(new Long[]{1L, 2L, 3L}, tree.getValues());
        
        tree.insert(4L, 4L, null);
        
        assertArrayEquals(new Long[]{1L, 2L, 3L, 4L}, tree.getKeys());
        assertArrayEquals(new Long[]{1L, 2L, 3L, 4L}, tree.getValues());
    }
    
    @Test
    public void testFindLocation() {
        tree.setKeys(new Long[]{1L});
        
        assertEquals(0, tree.findLocation(0L));
        assertEquals(1, tree.findLocation(2L));
        
        tree.setKeys(new Long[]{2L,5L});
        
        assertEquals(0, tree.findLocation(0L));
        assertEquals(1, tree.findLocation(3L));
        assertEquals(2, tree.findLocation(6L));
    }
    
    @Test
    public void testSplitLeaf() {
        tree.setKeys(null);
        
        assertNull(tree.splitLeaf());
        
        tree.setKeys(new Long[] {1L});
        
        assertNull(tree.splitLeaf());
        
        tree.setKeys(new Long[] {1L, 2L});
        
        assertNull(tree.splitLeaf());
        
        tree.setKeys(new Long[] {1L, 2L, 3L});
        
        assertNull(tree.splitLeaf());
        
        tree.setKeys(new Long[] {1L, 2L, 3L, 4L});
        
        assertNull(tree.splitLeaf());
        
        tree.setKeys(new Long[] {1L, 2L, 3L, 4L, 5L});
        
        BTreeNode.InsertResult result = tree.splitLeaf();
        
        assertNotNull(result);
        
        assertArrayEquals(new Long[]{1L, 2L, 3L}, result.getLeft().getKeys());
        assertArrayEquals(new Long[]{4L, 5L}, result.getRight().getKeys());
        assertEquals(3L, result.getMedian());
    }
    
    @Test
    public void testSplitDirectory() {
        tree.setKeys(null);
        tree.setType(BTreeNodeType.DIRECTORY);
        
        assertNull(tree.splitDirectory());
        
        tree.setKeys(new Long[] {1L});
        
        assertNull(tree.splitDirectory());
        
        tree.setKeys(new Long[] {1L, 2L});
        
        assertNull(tree.splitDirectory());
        
        tree.setKeys(new Long[] {1L, 2L, 3L});
        
        assertNull(tree.splitDirectory());
        
        tree.setKeys(new Long[] {1L, 2L, 3L, 4L});
        
        assertNull(tree.splitDirectory());
        
        tree.setKeys(new Long[] {1L, 2L, 3L, 4L, 5L});
        
        BTreeNode b1 = new BTreeNode();
        BTreeNode b2 = new BTreeNode();
        BTreeNode b3 = new BTreeNode();
        BTreeNode b4 = new BTreeNode();
        BTreeNode b5 = new BTreeNode();
        BTreeNode b6 = new BTreeNode();
        
        tree.setPointers(new BTreeNode[]{b1, b2, b3, b4, b5, b6});
        
        BTreeNode.InsertResult result = tree.splitDirectory();
        
        assertNotNull(result);
        
        assertArrayEquals(new Long[]{1L, 2L}, result.getLeft().getKeys());
        assertArrayEquals(new Long[]{4L, 5L}, result.getRight().getKeys());
        assertEquals(3L, result.getMedian());
        
        assertArrayEquals(new BTreeNode[]{b1, b2, b3}, result.getLeft().getPointers());
        assertArrayEquals(new BTreeNode[]{b4, b5, b6}, result.getRight().getPointers());
    }
    
    @Test
    public void test() {
        
    }

}
