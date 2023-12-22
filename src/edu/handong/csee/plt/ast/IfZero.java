package edu.handong.csee.plt.ast;

public class IfZero extends AST {
    AST testExpression;
    AST thenExpression;
    AST elseExpression;

    public IfZero(AST testExpression, AST thenExpression, AST elseExpression) {
        this.testExpression = testExpression;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
    }

    public AST getTestExpression() {
        return testExpression;
    }

    public void setTestExpression(AST testExpression) {
        this.testExpression = testExpression;
    }

    public AST getThenExpression() {
        return thenExpression;
    }

    public void setThenExpression(AST thenExpression) {
        this.thenExpression = thenExpression;
    }

    public AST getElseExpression() {
        return elseExpression;
    }

    public void setElseExpression(AST elseExpression) {
        this.elseExpression = elseExpression;
    }
    @Override
    public String toString() {
        return "(if0 " + getTestExpression().toString() + " " + getThenExpression().toString() + " " + getElseExpression().toString() + ")";
    }
    public String getASTCode() {
        return "(if0 " + getTestExpression().getASTCode() + " " + getThenExpression().getASTCode() + " " + getElseExpression().getASTCode() + ")";
    }
}
