package lrn.operation;

public class AndBlock extends Block implements Operation {
    @Override
    public boolean getResult() {
        for (Operation op:getOperations()) {
            if (!op.getResult()) {
                return false;
            }
        }
        
        return true;
    }    
}
