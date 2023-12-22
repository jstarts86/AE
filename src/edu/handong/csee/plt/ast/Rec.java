package edu.handong.csee.plt.ast;

public class Rec extends AST {
    String name;
    AST expression;
    AST functionCall;

    public Rec(String name, AST expression, AST functionCall) {
        this.name = name;
        this.expression = expression;
        this.functionCall = functionCall;
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

    public AST getFunctionCall() {
        return functionCall;
    }

    public void setFunctionCall(AST functionCall) {
        this.functionCall = functionCall;
    }

    public String getASTCode() {
        return "(rec " + getName() + " " + getExpression().getASTCode() + " " + getFunctionCall().getASTCode() + ")";
    }
    @Override
    public String toString() {
        return "(rec " + getName() + " " + getExpression().toString() + " " + getFunctionCall().toString() + ")";
    }
}
