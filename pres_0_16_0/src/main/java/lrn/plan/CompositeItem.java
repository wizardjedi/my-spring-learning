package lrn.plan;

public abstract class CompositeItem implements PlanItem {
    protected PlanItem innerItem;

    public CompositeItem() {
    }

    public CompositeItem(PlanItem innerItem) {
        this.innerItem = innerItem;
    }

    public PlanItem getInnerItem() {
        return innerItem;
    }

    public void setInnerItem(PlanItem innerItem) {
        this.innerItem = innerItem;
    }

    @Override
    public boolean isStreamItem() {
        return innerItem.isStreamItem();
    }
}
