package lrn.operation;

import lrn.recordset.Row;

public class AndBlock extends Block implements Operation {
    @Override
    public boolean getResult(Row row) {
        for (Operation op:getOperations()) {
            if (!op.getResult(row)) {
                return false;
            }
        }
        
        return true;
    }    
}
