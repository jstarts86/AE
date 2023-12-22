package edu.handong.csee.plt.ast;

public class Seqn extends AST {
    AST ex1;
    AST ex2;

    public Seqn(AST ex1, AST ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    public AST getEx1() {
        return ex1;
    }

    public void setEx1(AST ex1) {
        this.ex1 = ex1;
    }

    public AST getEx2() {
        return ex2;
    }

    public void setEx2(AST ex2) {
        this.ex2 = ex2;
    }

    public String getASTCode() {
        return "{seqn " + ex1.getASTCode() + " " + ex2.getASTCode() + "}";
    }
    @Override
    public String toString() {
        return "{seqn " + ex1.toString() + " " + ex2.toString() + "}";
    }
}
