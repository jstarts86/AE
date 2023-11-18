package edu.handong.csee.plt.rcfaevalue;

import edu.handong.csee.plt.Box;
import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defsub.DefrdSub;

public class ExpressionV extends RCFAEValue{

    AST expression;
    Box<RCFAEValue> valueBox;
    DefrdSub ds;

    public ExpressionV(AST expression, Box<RCFAEValue> valueBox, DefrdSub ds) {
        this.expression = expression;
        this.valueBox = valueBox;
        this.ds = ds;
    }

    public AST getExpression() {
        return expression;
    }

    public void setExpression(AST expression) {
        this.expression = expression;
    }

    public Box<RCFAEValue> getValueBox() {
        return valueBox;
    }

    public void setValueBox(Box<RCFAEValue> valueBox) {
        this.valueBox = valueBox;
    }

    public DefrdSub getDs() {
        return ds;
    }

    public void setDs(DefrdSub ds) {
        this.ds = ds;
    }


    @Override
    public String getRCFAEValueCode() {
        return "(exprV " + getExpression() + " " + getDs() + " " +getValueBox() +")";
    }

}
