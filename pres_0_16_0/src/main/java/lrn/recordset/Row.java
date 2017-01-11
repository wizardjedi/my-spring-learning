package lrn.recordset;

import java.util.List;

public class Row {
   
    protected List<Object> values;

    public static Row create(List values) {
        Row r = new Row();
        r.setValues(values);
        
        return r;
    }
    
    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Row{" + "values=" + values + '}';
    }    
}
