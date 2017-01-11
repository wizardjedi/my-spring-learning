package lrn.recordset;

import java.util.ArrayList;
import java.util.List;

public class RecordSet {
    protected List<Column> columns;
    
    protected List<Row> rows = new ArrayList<>();

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }    
    
    public void addRow(Row row) {
        getRows().add(row);
    }
    
    public RecordSet combine(RecordSet rs) {
        this.rows.addAll(rs.getRows());
        
        return this;
    }

    @Override
    public String toString() {
        return "RecordSet{" + "columns=" + columns + ", rows=" + rows + '}';
    }    
}
