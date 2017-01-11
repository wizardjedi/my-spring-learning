package lrn.plan;

import lrn.recordset.RecordSet;
import org.junit.Test;

public class FetchTableRowItemTest {
    
    
    @Test
    public void testFetchResultRecordSet() {
        FetchTableRowItem item = new FetchTableRowItem("src/main/resources/data/", "person");
        
        RecordSet rs = item.fetchResultRecordSet();
        
        System.out.println("Table="+item.getTable());
        System.out.println("Rs="+rs.toString());
    }
    
}
