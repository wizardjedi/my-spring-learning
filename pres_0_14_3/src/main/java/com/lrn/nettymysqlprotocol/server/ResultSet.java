package com.lrn.nettymysqlprotocol.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSet implements ServerObject {
    protected List<Column> columns = new ArrayList<>();
    
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

    public static class Row {
        protected List<Value> values = new ArrayList<>();

        public Row() {
        }

        public Row(Object... values) {
            Value[] tmpValues = new Value[values.length];
            
            for (int i=0;i<values.length;i++) {
                tmpValues[i] = new Value(values[i]);
            }
            
            this.values = Arrays.asList(tmpValues);
        }
        
        public Row(Value... values) {
            this.values = Arrays.asList(values);
        }
        
        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }
    }
    
    public static class Column {
        protected String name;

        public Column() {
        }

        public Column(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
    public static class Value {
        protected Object value;

        public Value() {
        }

        public Value(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
