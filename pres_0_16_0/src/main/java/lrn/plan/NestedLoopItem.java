package lrn.plan;

import java.util.ArrayList;
import lrn.operation.Operation;
import lrn.operation.ValueResolver;
import lrn.recordset.RecordSet;
import lrn.recordset.Row;

public class NestedLoopItem implements PlanItem {

    protected PlanItem left;
    protected PlanItem right;
    protected ValueResolver leftResolver;
    protected ValueResolver rightResolver;
    
    protected boolean lastRecordSet = true;

    public static NestedLoopItem create(PlanItem left, PlanItem right) {
        NestedLoopItem nlp = new NestedLoopItem();
        nlp.setLeft(left);
        nlp.setRight(right);

        return nlp;
    }

    @Override
    public RecordSet fetchResultRecordSet() {
        if (isStreamItem()) {
            //
            return null;
        } else {
            RecordSet rs1 = left.fetchResultRecordSet();
            RecordSet rs2 = right.fetchResultRecordSet();

            RecordSet resultRecordSet = new RecordSet();

            resultRecordSet.setColumns(new ArrayList<>());
            resultRecordSet.getColumns().addAll(rs1.getColumns());
            resultRecordSet.getColumns().addAll(rs2.getColumns());

            for (Row row1 : rs1.getRows()) {
                for (Row row2 : rs2.getRows()) {
                    if (
                        leftResolver == null 
                        || leftResolver.resolve(row1).equals(rightResolver.resolve(row2))
                    ) {
                        Row r = new Row();
                        r.setValues(new ArrayList<>());

                        r.getValues().addAll(row1.getValues());
                        r.getValues().addAll(row2.getValues());

                        resultRecordSet.addRow(r);
                    }
                }
            }

            return resultRecordSet;
        }
    }

    @Override
    public boolean isStreamItem() {
        return left.isStreamItem() && right.isStreamItem();
    }

    public PlanItem getLeft() {
        return left;
    }

    public void setLeft(PlanItem left) {
        this.left = left;
    }

    public PlanItem getRight() {
        return right;
    }

    public void setRight(PlanItem right) {
        this.right = right;
    }

    public ValueResolver getLeftResolver() {
        return leftResolver;
    }

    public void setLeftResolver(ValueResolver leftResolver) {
        this.leftResolver = leftResolver;
    }

    public ValueResolver getRightResolver() {
        return rightResolver;
    }

    public void setRightResolver(ValueResolver rightResolver) {
        this.rightResolver = rightResolver;
    }
}
