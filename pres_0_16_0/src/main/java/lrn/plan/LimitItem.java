package lrn.plan;

import lrn.recordset.RecordSet;

public class LimitItem extends CompositeItem {

    protected long maxItems;
    
    protected long currentItem = 0;
    
    public static LimitItem create(PlanItem plan, long max) {
        LimitItem limitItem = new LimitItem();
        limitItem.setInnerItem(plan);
        limitItem.setMaxItems(max);
        limitItem.setCurrentItem(0);
        
        return limitItem;
    }
    
    @Override
    public RecordSet fetchResultRecordSet() {
        if (innerItem.isStreamItem()) {
            if (currentItem < maxItems) {
                currentItem++;
                return innerItem.fetchResultRecordSet();
            }
        } else {
            RecordSet resultRs = innerItem.fetchResultRecordSet();
            
            RecordSet rs = new RecordSet();
            rs.setColumns(resultRs.getColumns());
            rs.setRows(resultRs.getRows().subList(0, (int) maxItems));
            
            return rs;
        }
        
        return null;
    }

    public long getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(long maxItems) {
        this.maxItems = maxItems;
    }

    public long getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(long currentItem) {
        this.currentItem = currentItem;
    }
    
    
    
}
