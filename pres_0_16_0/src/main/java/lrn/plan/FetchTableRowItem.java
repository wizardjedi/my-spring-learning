package lrn.plan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lrn.recordset.Column;
import lrn.recordset.RecordSet;
import lrn.recordset.Row;
import lrn.table.Table;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class FetchTableRowItem extends BaseItem implements PlanItem {

    protected Table table;
    
    protected String tableName;
    
    protected ICsvListReader listReader;
    
    public FetchTableRowItem(String path, String tableName) {
        super(true);
        
        try {
            this.tableName = tableName;
            
            this.table = new Table();
            this.table.setName(tableName);
            
            listReader =
                    new CsvListReader(
                            new FileReader(path + tableName + ".csv"),
                            CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE
                    );
            
            List<String> list = listReader.read();
            
            for (String columnName:list) {
                table.getColumns().add(new Column(columnName));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FetchTableRowItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FetchTableRowItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public RecordSet fetchResultRecordSet() {
        try {
            List<String> list = listReader.read();
            
            RecordSet rs = new RecordSet();
            rs.setColumns(table.getColumns());
            rs.addRow(Row.create(list));
            
            return rs;
        } catch (IOException e) {
            
        }
        
        return null;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ICsvListReader getListReader() {
        return listReader;
    }

    public void setListReader(ICsvListReader listReader) {
        this.listReader = listReader;
    }
    
    
}
