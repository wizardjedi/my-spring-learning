package lrn.plan;

public abstract class BaseItem implements PlanItem {
    protected boolean streamItem;

    public BaseItem(boolean streamItem) {
        this.streamItem = streamItem;
    }

    @Override
    public boolean isStreamItem() {
        return this.streamItem;
    }
}
