package lrn.table;

import java.util.ArrayList;
import java.util.List;
import lrn.recordset.Column;

public class Table {
    protected String name;
    protected List<Column> columns = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" + "name=" + name + ", columns=" + columns + '}';
    }
}
