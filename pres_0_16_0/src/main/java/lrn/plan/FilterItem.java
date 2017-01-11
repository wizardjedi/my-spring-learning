package lrn.plan;

import lrn.operation.Operation;
import lrn.recordset.RecordSet;
import lrn.recordset.Row;

public class FilterItem extends CompositeItem {
    protected Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public FilterItem(Operation operation) {
        this.operation = operation;
    }

    public FilterItem(Operation operation, PlanItem innerItem) {
        super(innerItem);
        this.operation = operation;
    }

    @Override
    public RecordSet fetchResultRecordSet() {
        RecordSet resultRs = new RecordSet();
        
        RecordSet rs;
        do {
            rs = innerItem.fetchResultRecordSet();
            
            if (rs != null) {
                resultRs.setColumns(rs.getColumns());
                
                for (Row row:rs.getRows()) {
                    if (operation.getResult(row)) {
                        resultRs.addRow(row);
                    }
                }
            }
        } while (rs != null);
        
        return resultRs;
    }

    @Override
    public boolean isStreamItem() {
        return false;
    }
}
