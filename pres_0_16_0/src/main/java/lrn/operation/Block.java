package lrn.operation;

import java.util.ArrayList;
import java.util.List;

public class Block {
    protected List<Operation> operations;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    
    public void addOperation(Operation op) {
        if (getOperations() == null) {
            setOperations(new ArrayList<>());
        }
        
        getOperations().add(op);
    }
}
