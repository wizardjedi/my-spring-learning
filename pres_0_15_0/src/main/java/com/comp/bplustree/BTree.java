package com.comp.bplustree;

public class BTree<KeyClass extends Comparable, ValueClass> {
    protected BTreeNode<KeyClass, ValueClass> root;

    public BTreeNode getRoot() {
        return root;
    }

    public void setRoot(BTreeNode root) {
        this.root = root;
    }
    
    public void insert(KeyClass key, ValueClass value) {
        if (root == null) {
            root = BTreeNode.createLeaf(null, null);
        } 
        
        if (root.ckeckExists(key)) {
            throw new DuplicateKeyException();
        }
        
        BTreeNode.InsertResult insertResult = root.insert(key, value, this);
        
        if (insertResult != null) {
            
            BTreeNode newRoot = 
                BTreeNode
                    .createDirectory(
                            new Comparable[]{insertResult.median},
                            new BTreeNode[] {insertResult.getLeft(), insertResult.getRight()}
                    );
            
            root = newRoot;
        }
    }
    
    public boolean ckeckExists(KeyClass key) {
        return getRoot().ckeckExists(key);
    }
    
    public ValueClass search(KeyClass key) {
        if (root == null) {
            return null;
        } else {
            return root.search(key);
        }
    }

    public void delete(KeyClass key) {
        if (getRoot() != null) {
            getRoot().delete(key);
        }
    }
    
    public static class DuplicateKeyException extends RuntimeException {
        
    }
}
