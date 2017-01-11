package lrn.operation;

public class EqualOperation extends BinaryOperation implements Operation {

    public EqualOperation(Object left, Object right) {
        super(left, right);
    }

    @Override
    public boolean getResult() {
        return left.equals(right);        
    }    
}
