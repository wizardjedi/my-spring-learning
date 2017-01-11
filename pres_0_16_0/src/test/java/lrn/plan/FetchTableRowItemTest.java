package lrn.plan;

import lrn.operation.ColumnValue;
import lrn.operation.ConstValue;
import lrn.operation.LikeOperation;
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
        
        nlp.setLeftResolver(new ColumnValue(item.getTable().getColumns(), "department_id"));
        
        nlp.setRightResolver(new ColumnValue(item2.getTable().getColumns(), "id"));
        
        System.err.println("Nlp="+nlp.fetchResultRecordSet());
    }
    
    @Test
    public void testFetchResultRecordSet3() {
        FetchTableRowItem item = new FetchTableRowItem("src/main/resources/data/", "task");
        
        FilterItem fi = 
            new FilterItem(
                new LikeOperation(
                        new ColumnValue(item.getTable().getColumns(), "status"),
                        new ConstValue("3")
                ), 
                item
            );
        
        System.out.println("Filter="+ fi.fetchResultRecordSet());
    }
}
