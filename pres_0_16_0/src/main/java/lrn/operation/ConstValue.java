package lrn.operation;

import lrn.recordset.Row;

public class ConstValue implements ValueResolver {
    protected Object value;

    public ConstValue() {
    }

    public ConstValue(Object value) {
        this.value = value;
    }
    
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object resolve(Row row) {
        return this.value;
    }
}
