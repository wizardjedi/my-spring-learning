package lrn.operation;

import lrn.recordset.Row;

public class LikeOperation extends BinaryOperation implements Operation {
    public LikeOperation(ValueResolver left, ValueResolver right) {
        super(left, right);
    }

    @Override
    public boolean getResult(Row row) {
        String r1 = ((ValueResolver)left).resolve(row).toString();
        String r2 = ((ValueResolver)right).resolve(row).toString();
        
        return r1.matches(r2);
    }
}
