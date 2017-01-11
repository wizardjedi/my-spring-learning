package lrn.operation;

import lrn.recordset.Row;

public class EqualOperation extends BinaryOperation implements Operation {

    public EqualOperation(ValueResolver left, ValueResolver right) {
        super(left, right);
    }

    @Override
    public boolean getResult(Row row) {
        Object r1 = ((ValueResolver)left).resolve(row);
        Object r2 = ((ValueResolver)right).resolve(row);
        
        return r1.equals(r2);
    }
}
