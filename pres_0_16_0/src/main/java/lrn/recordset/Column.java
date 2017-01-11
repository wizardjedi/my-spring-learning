package lrn.recordset;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Column other = (Column) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
