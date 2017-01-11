package lrn.plan;

import com.sun.org.apache.xml.internal.utils.StringComparable;
import java.util.Collections;
import java.util.Comparator;
import lrn.recordset.RecordSet;
import lrn.recordset.Row;

public class SortItem extends CompositeItem {

    protected int sortIndex = 0;
    
    protected boolean asc = true;

    public SortItem(PlanItem innerItem) {
        super(innerItem);
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    @Override
    public RecordSet fetchResultRecordSet() {        
        RecordSet rs1 = innerItem.fetchResultRecordSet();
        
        RecordSet rs2;
        
        do {
            rs2 = innerItem.fetchResultRecordSet();
            
            if (rs2 != null) {
                rs1.combine(rs2);
            }
        } while (rs2 != null);
        
        Collections
            .sort(
                rs1.getRows(), 
                new Comparator<Row>() {
                    @Override
                    public int compare(Row o1, Row o2) {
                        String v1 = o1.getValues().get(sortIndex).toString();
                        String v2 = o2.getValues().get(sortIndex).toString();
                        
                        return v1.compareTo(v2) * (asc ? 1 : -1);
                    }
                }
            );
        
        return rs1;
    }

    @Override
    public boolean isStreamItem() {
        return false;
    }
    
    
}
