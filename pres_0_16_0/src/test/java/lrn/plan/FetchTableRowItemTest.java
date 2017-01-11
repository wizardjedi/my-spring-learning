package lrn.plan;

import lrn.recordset.RecordSet;
import org.junit.Test;

public class FetchTableRowItemTest {
    
    
    @Test
    public void testFetchResultRecordSet() {
        FetchTableRowItem item = new FetchTableRowItem("src/main/resources/data/", "person");
        
        SortItem si = new SortItem(item);
        si.setAsc(false);
        
        LimitItem li = LimitItem.create(si, 10);
        
        System.err.println("Rs="+li.fetchResultRecordSet());
    }    
    
    @Test
    public void testFetchResultRecordSet2() {
        FetchTableRowItem item = new FetchTableRowItem("src/main/resources/data/", "person");
        
        SortItem si = new SortItem(item);
        si.setAsc(false);
        
        LimitItem li = LimitItem.create(si, 10);
        
        FetchTableRowItem item2 = new FetchTableRowItem("src/main/resources/data/", "department");
        
        SortItem si2 = new SortItem(item2);
        
        LimitItem li2 = LimitItem.create(si2, 10);
        
        NestedLoopItem nlp = NestedLoopItem.create(li, li2);
        
        System.err.println("Nlp="+nlp.fetchResultRecordSet());
    }  
}
