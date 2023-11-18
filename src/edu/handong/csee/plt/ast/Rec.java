package edu.handong.csee.plt.ast;

import edu.handong.csee.plt.defsub.DefrdSub;

public class Rec extends AST {
    private String name;
    private AST expression;

    private AST fstCall;

    public Rec(String name, AST expression, AST fstCall) {
        this.name = name;
        this.expression = expression;
        this.fstCall = fstCall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AST getExpression() {
        return expression;
    }

    public void setExpression(AST expression) {
        this.expression = expression;
    }

    public AST getFstCall() {
        return fstCall;
    }

    public void setFstCall(AST fstCall) {
        this.fstCall = fstCall;
    }

    @Override
    public String getASTCode() {
        return "(rec " + getName() + " " + getExpression().getASTCode() + " " + getFstCall().getASTCode() + ")";
    }
    @Override
    public String toString() {
        return "(rec " + getName() + " " + getExpression().getASTCode() + " " + getFstCall().getASTCode() + ")";

    }
}
