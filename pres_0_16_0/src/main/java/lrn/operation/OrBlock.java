package lrn.operation;

public class OrBlock extends Block implements Operation {
    @Override
    public boolean getResult() {
        for (Operation op:getOperations()) {
            if (op.getResult()) {
                return true;
            }
        }
        
        return false;
    }  
}
