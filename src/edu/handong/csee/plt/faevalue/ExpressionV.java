package edu.handong.csee.plt.faevalue;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.ast.NewBox;
import edu.handong.csee.plt.defsub.DefrdSub;

public class ExpressionV extends RBMRFAEValue{
    AST expression;
    DefrdSub ds;

    NewBox valueBox;

    public AST getExpression() {
        return expression;
    }

    public void setExpression(AST expression) {
        this.expression = expression;
    }

    public DefrdSub getDs() {
        return ds;
    }

    public void setDs(DefrdSub ds) {
        this.ds = ds;
    }

    public NewBox getValueBox() {
        return valueBox;
    }

    public void setValueBox(NewBox valueBox) {
        this.valueBox = valueBox;
    }

    public ExpressionV(AST expression, DefrdSub ds, NewBox valueBox) {
        this.expression = expression;
        this.ds = ds;
        this.valueBox = valueBox;
    }
}
