package lrn.plan;

import lrn.recordset.RecordSet;
import lrn.recordset.Row;

public interface PlanItem {
    public boolean isStreamItem();
    
    public RecordSet fetchResultRecordSet();
}
