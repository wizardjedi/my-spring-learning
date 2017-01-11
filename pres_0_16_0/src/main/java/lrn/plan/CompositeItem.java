package lrn.plan;

public class CompositeItem {
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
}
