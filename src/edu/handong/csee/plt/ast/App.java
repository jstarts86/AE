package edu.handong.csee.plt.ast;

public class App extends AST{

    AST funExpr = new AST();
    AST argExpr = new AST();

    public App(AST funExpr, AST argExpr) {
        this.funExpr = funExpr;
        this.argExpr = argExpr;
    }

    public AST getFunExpr() {
        return funExpr;
    }

    public AST getArgExpr() {
        return argExpr;
    }

    public String getASTCode() {
        return "(app " + funExpr.getASTCode() + " " + argExpr.getASTCode() + " )";
    }



}
