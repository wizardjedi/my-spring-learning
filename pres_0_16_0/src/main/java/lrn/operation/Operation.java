package lrn.operation;

import lrn.recordset.RecordSet;
import lrn.recordset.Row;

public interface Operation {
    public boolean getResult(Row row);
}
