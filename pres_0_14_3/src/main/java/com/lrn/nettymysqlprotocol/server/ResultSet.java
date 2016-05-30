package com.lrn.nettymysqlprotocol.server;

import java.util.List;

public class ResultSet implements ServerObject {
    protected List<Column> columns;
    
    protected List<Value> values;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
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
