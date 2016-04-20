package com.comp.bplustree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class BTreeNode<KeyClass extends Comparable, ValueClass> {

    public static final int MAX_LEAFS = 4;

    public static final float FILL_FACTOR = 0.5f;
    
    protected BTreeNodeType type = BTreeNodeType.LEAF;

    protected BTreeNode<KeyClass, ValueClass> parent = null;
    protected KeyClass[] keys = null;
    protected ValueClass[] values = null;
    protected BTreeNode[] pointers = null;

    protected BTreeNode left;
    protected BTreeNode right;
    
    public static BTreeNode createLeaf(Comparable[] keys, Object[] values) {
        BTreeNode newNode = new BTreeNode();
        
        newNode.setKeys(keys);
        newNode.setValues(values);
        
        return newNode;
    }
    
    public static BTreeNode createDirectory(Comparable[] keys, BTreeNode[] pointers) {
        BTreeNode newNode = new BTreeNode();
        
        newNode.setKeys(keys);
        newNode.setPointers(pointers);
        newNode.setType(BTreeNodeType.DIRECTORY);
        
        return newNode;
    }

    public BTreeNode getLeft() {
        return left;
    }

    public void setLeft(BTreeNode left) {
        this.left = left;
    }

    public BTreeNode getRight() {
        return right;
    }

    public void setRight(BTreeNode right) {
        this.right = right;
    }
    
    public BTreeNode<KeyClass, ValueClass> getParent() {
        return parent;
    }

    public void setParent(BTreeNode<KeyClass, ValueClass> parent) {
        this.parent = parent;
    }
    
    public BTreeNodeType getType() {
        return type;
    }

    public void setType(BTreeNodeType type) {
        this.type = type;
    }

    public KeyClass[] getKeys() {
        return keys;
    }

    public void setKeys(KeyClass[] keys) {
        this.keys = keys;
    }

    public ValueClass[] getValues() {
        return values;
    }

    public void setValues(ValueClass[] values) {
        this.values = values;
    }

    public BTreeNode[] getPointers() {
        return pointers;
    }

    public void setPointers(BTreeNode[] pointers) {
        this.pointers = pointers;
    }

    public boolean isFull() {
        return !(values.length < MAX_LEAFS);
    }
    
    public boolean isNotFull() {
        return (values.length < MAX_LEAFS);
    }
    
    public boolean isEmpty() {
        return (values == null) || (values.length == 0);
    }
    
    public InsertResult insert(KeyClass key, ValueClass value, BTree tree) {
        if (isLeaf()) {
            int location = findLocation(key);
            
            setKeys(ArrayUtils.insertToArray(getKeys(), key, location));
            setValues(ArrayUtils.insertToArray(getValues(), value, location));
            
            if (getKeys().length > MAX_LEAFS) {
                return splitLeaf();
            }
        } else {
            BTreeNode<KeyClass, ValueClass> subTree = findSubTree(key);
            
            InsertResult insertResult = subTree.insert(key, value, tree);
            
            if (insertResult != null) {
                int location = findLocation((KeyClass) insertResult.getMedian());

                setKeys(ArrayUtils.insertToArray(getKeys(), (KeyClass)insertResult.getMedian(), location));
                
                BTreeNode[] newPointers = ArrayUtils.insertToArray(getPointers(), insertResult.getRight(), location+1);
                
                setPointers(newPointers);
                
                if (getKeys().length > MAX_LEAFS) {
                    InsertResult splitDirectory = splitDirectory();
                    
                    return splitDirectory;
                }
            }
        }
        
        return null;
    }
    
    public int findLocation(KeyClass key) {
        return ArrayUtils.findLocation(keys, key);
    }
    
    /**
     *
     * @param key
     * @return
     */
    public boolean ckeckExists(KeyClass key) {
        // in simple case use linear search
        // in prod should use binary search
        
        if (type == BTreeNodeType.LEAF) {
            // search in values for leaf node
            if (keys != null && keys.length > 0) {
                for (int i = 0; i < keys.length; i++) {
                    if (keys[i].equals(key)) {
                        return true;
                    }
                }
            }
        } else { // search in directory nodes
            if (pointers != null && pointers.length > 0) {
                return findSubTree(key).ckeckExists(key);
            }
        }

        return false;
    }
    
    /**
     *
     * @param key
     * @return
     */
    public ValueClass search(KeyClass key) {
        // in simple case use linear search
        // in prod should use binary search
        
        if (type == BTreeNodeType.LEAF) {
            // search in values for leaf node
            if (keys != null && keys.length > 0) {
                for (int i = 0; i < keys.length; i++) {
                    if (keys[i].equals(key)) {
                        return values[i];
                    }
                }
            }
        } else { // search in directory nodes
            if (pointers != null && pointers.length > 0) {
                return findSubTree(key).search(key);
            }
        }

        return null;
    }
    
    public ValueClass getMinValue() {
        if (getKeys() != null && getKeys().length > 0) {
            if (isLeaf()) {
                return getValues()[0];
            } else {
                return (ValueClass)getPointers()[0].getMinValue();
            }
        }
        
        return null;
    }
    
    public KeyClass getMinKey() {
        if (getKeys() != null && getKeys().length > 0) {
            if (isLeaf()) {
                return getKeys()[0];
            } else {
                return (KeyClass)getPointers()[0].getMinKey();
            }
        }
        
        return null;
    }
    
    public ValueClass getMaxValue() {
        if (getKeys() != null && getKeys().length > 0) {
            int len = getKeys().length;
            
            if (isLeaf()) {
                return getValues()[len-1];
            } else {
                return (ValueClass)getPointers()[len].getMinValue();
            }
        }
        
        return null;
    }
    
    public KeyClass getMaxKey() {
        if (getKeys() != null && getKeys().length > 0) {
            int len = getKeys().length;
            
            if (isLeaf()) {
                return getKeys()[len-1];
            } else {
                return (KeyClass)getPointers()[len].getMinKey();
            }
        }
        
        return null;
    }
    
    public BTreeNode<KeyClass, ValueClass> findSubTree(Comparable key) {
        if (keys[0].compareTo(key) >= 0) {
            return pointers[0];
        }
        
        if (keys[keys.length-1].compareTo(key) >= 0) {
            return pointers[keys.length-1];
        }
        
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].compareTo(key) >= 0) {
                return pointers[i];
            }
        }
        
        return pointers[pointers.length - 1];
    }
    
    public boolean isLeaf() {
        return getType() == BTreeNodeType.LEAF;
    }
    
    public boolean isDirectory() {
        return getType() == BTreeNodeType.DIRECTORY;
    }
    
    
    
    public static boolean checkMergeableToOne(BTreeNode left, BTreeNode right) {
        int leftCount = 0;
        
        if (left != null && left.getKeys() != null) {
            leftCount = left.getKeys().length;
        }
        
        int rightCount = 0;
        
        if (right != null && right.getKeys() != null) {
            rightCount = right.getKeys().length;
        }
        
        return (leftCount + rightCount) <= MAX_LEAFS;
    }
    
    public static int checkMergeable(BTreeNode left, BTreeNode right) {
        int leftCount = 0;
        
        if (left != null && left.getKeys() != null) {
            leftCount = left.getKeys().length;
        }
        
        if (leftCount < MAX_LEAFS) {
            return -1;
        }
        
        int rightCount = 0;
        
        if (right != null && right.getKeys() != null) {
            rightCount = right.getKeys().length;
        }
        
        if (rightCount < MAX_LEAFS) {
            return 1;
        }
        
        return 0;
    }
    
    public void delete(KeyClass key) {
        if (isLeaf()) {
            int loc = ArrayUtils.findPosition(getKeys(), key);                        
            
            if (loc >=0) {
                setKeys(ArrayUtils.removeFromArray(getKeys(), loc));
                setValues(ArrayUtils.removeFromArray(getValues(), loc));
            }
        } else {
            BTreeNode<KeyClass, ValueClass> subTree = findSubTree(key);
            
            subTree.delete(key);
        }
    }
    
    /**
     * Split leaf node. Middle element will be the last in left array.
     * @return 
     */
    public InsertResult splitLeaf() {
        if (isLeaf()) {
            if (
                getKeys() != null 
                && getKeys().length > MAX_LEAFS
            ) {
                int len = getKeys().length;

                int factor = (int)Math.floor(len * FILL_FACTOR) + 1;
                System.out.println("Len="+len+" factor="+factor );
                int newNodeLength = len - factor ;

                KeyClass middle = (KeyClass)getKeys()[factor-1];

                BTreeNode newNode = 
                    BTreeNode
                        .createLeaf(
                            ArrayUtils.subArray((Comparable [])getKeys(), factor, newNodeLength), 
                            ArrayUtils.subArray(getValues(), factor, newNodeLength)
                        );

                setKeys((KeyClass [])ArrayUtils.subArray(getKeys(), 0, factor));
                setValues((ValueClass [])ArrayUtils.subArray(getValues(), 0, factor));

                if (this.getRight() != null) {
                    this.getRight().setLeft(newNode);
                }
                
                newNode.setRight(this.getRight());
                newNode.setLeft(this);
                
                this.setRight(newNode);
                
                return InsertResult.create(this, newNode, (Comparable)middle);
            }
        } 
            
        return null;
    }
    
    /**
     * Split directory node. Middle element will be middle.
     * @return 
     */
    public InsertResult splitDirectory() {
        if (isDirectory()) {
            if (
                getKeys() != null 
                && getKeys().length > MAX_LEAFS
            ) {
                int len = getKeys().length;

                int factor = (int)Math.floor(len * FILL_FACTOR) + 1;

                int newNodeLength = len - factor ;

                KeyClass middle = (KeyClass)getKeys()[factor-1];
                
                BTreeNode newNode = 
                    BTreeNode
                        .createDirectory(
                            ArrayUtils.subArray((Comparable [])getKeys(), factor, newNodeLength), 
                            ArrayUtils.subArray(getPointers(), factor, newNodeLength+1)
                        );

                setKeys((KeyClass [])ArrayUtils.subArray(getKeys(), 0, factor-1));
                setPointers((BTreeNode[])ArrayUtils.subArray(getPointers(), 0, factor));

                if (this.getRight() != null) {
                    this.getRight().setLeft(newNode);
                }
                
                newNode.setRight(this.getRight());
                newNode.setLeft(this);
                
                this.setRight(newNode);
                
                return InsertResult.create(this, newNode, (Comparable)middle);
            }
        } 
            
        return null;
    }
    
    /**
     * Check weather insert avail
     * @return 
     */
    public boolean isInsertable() {
        return values.length <= MAX_LEAFS;
    }
    
    public static void buildGraphviz(BTreeNode root,String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(fileName));
        
        BufferedWriter bw = new BufferedWriter(fileWriter);
        
        bw.write("digraph g {\nnode [shape = record,height=.1];\n");
        
        bw.write("null [label=\"null\"];\n");
        
        root.buildGraphViz(bw, 0);
        
        bw.write("\n}");
        
        bw.flush();
        bw.close();
    }
    
    public void buildGraphViz(Writer bw, long level) throws IOException {
        //bw.write("subgraph level"+level+"{\nrankdir=\"same\";");
        bw.write("node"+System.identityHashCode(this)+"[");
        
        bw.write("color="+(isLeaf() ? "red," : "blue,"));
        
        bw.write("label=\"");
                
        //bw.write("<left>L|");
        
        if (type == BTreeNodeType.LEAF) {
            if (keys.length > 0) {
                for (int i=0;i<keys.length;i++) {
                    bw.write(String.valueOf(keys[i]));
                    
                    if (i < keys.length-1) {
                        bw.write("|");
                    }
                }
            }
        } else {
            if (keys.length > 0) {
                for (int i=0;i<keys.length;i++) {
                    bw.write("<f"+i+">|");
                    bw.write(String.valueOf(keys[i]));
                    
                    if (i < keys.length-1) {
                        bw.write("|");
                    }
                }
                
                bw.write("|<f"+(keys.length)+">");
            }
        }
        
        //bw.write("|<right>R");
        
        bw.write("\"];\n");
        
        //bw.write("}\n");
        
        //bw.write("\"node"+System.identityHashCode(this)+"\":left->\""+(getLeft() != null ? "node"+System.identityHashCode(getLeft()) : "null")+"\";\n");
        //bw.write("\"node"+System.identityHashCode(this)+"\":right->\""+(getRight()!= null ? "node"+System.identityHashCode(getRight()) : "null")+"\";\n");
        
        if (type == BTreeNodeType.DIRECTORY) {
            for (int i=0;i<pointers.length;i++) {
                
                pointers[i].buildGraphViz(bw, level + 1);
                
                bw.write("\"node"+System.identityHashCode(this)+"\":f"+i+"->\"node"+(System.identityHashCode(pointers[i]))+"\";\n");
            }
        }
    }

    @Override
    public String toString() {
        return 
                "BTreeNode{type=" + type 
                    + ", keys=" + Arrays.toString(keys) 
                    + ", values=" + Arrays.toString(values) 
                    + ", pointers=" + Arrays.toString(pointers) 
                + '}';
    }    
    
    public static class InsertResult<KeyClass extends Comparable, ValueClass> {
        protected BTreeNode<KeyClass, ValueClass> left;
        protected BTreeNode<KeyClass, ValueClass> right;
        protected KeyClass median;

        public static InsertResult create(BTreeNode left, BTreeNode right, Comparable middle) {
            return new InsertResult(left, right, middle);
        }

        public InsertResult(BTreeNode<KeyClass, ValueClass> left, BTreeNode<KeyClass, ValueClass> right, KeyClass median) {
            this.left = left;
            this.right = right;
            this.median = median;
        }
        
        public BTreeNode<KeyClass, ValueClass> getLeft() {
            return left;
        }

        public void setLeft(BTreeNode<KeyClass, ValueClass> left) {
            this.left = left;
        }

        public BTreeNode<KeyClass, ValueClass> getRight() {
            return right;
        }

        public void setRight(BTreeNode<KeyClass, ValueClass> right) {
            this.right = right;
        }

        public KeyClass getMedian() {
            return median;
        }

        public void setMedian(KeyClass median) {
            this.median = median;
        }

        @Override
        public String toString() {
            return "InsertResult{" + "left=" + left + ", right=" + right + ", median=" + median + '}';
        }

        
        
        
    }
    
}
