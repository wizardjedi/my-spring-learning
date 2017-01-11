package lrn.recordset;

import lrn.table.ColumnTypeEnum;

public class Column {
    protected String name;

    protected ColumnTypeEnum type = ColumnTypeEnum.STRING;
    
    public Column(String name) {
        this.name = name;
    }

    public Column() {        
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Column{" + "name=" + name + '}';
    }    
}
