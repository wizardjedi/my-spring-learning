package lrn.operation;

import java.util.List;
import lrn.recordset.Column;
import lrn.recordset.Row;

public class ColumnValue implements ValueResolver {
    protected List<Column> columns;
    protected String columnName;

    public ColumnValue(List<Column> columns, String columnName) {
        this.columns = columns;
        this.columnName = columnName;
    }
    
    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    @Override
    public Object resolve(Row row) {
        int idx = columns.indexOf(new Column(columnName));
        
        return row.getValues().get(idx);
    }
}
