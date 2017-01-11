package lrn.operation;

public class BinaryOperation {
    protected Object left;
    protected Object right;

    public BinaryOperation(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    public BinaryOperation() {
    }
    
    public Object getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = left;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = right;
    }        
}
