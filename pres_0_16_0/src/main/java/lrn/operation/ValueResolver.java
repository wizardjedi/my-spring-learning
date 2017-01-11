package lrn.operation;

import lrn.recordset.Row;

public interface ValueResolver {
    public Object resolve(Row row);
}
