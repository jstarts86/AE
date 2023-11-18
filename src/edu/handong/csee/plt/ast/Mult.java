package edu.handong.csee.plt.ast;

public class Mult extends AST{
    AST lhs = new AST();
	AST rhs = new AST();

	public Mult(AST lhs, AST rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public AST getLhs() {
		return lhs;
	}

	public AST getRhs() {
		return rhs;
	}

	public String getASTCode() {
		return "(mult " + lhs.getASTCode() + " " + rhs.getASTCode() + ")";
	}
	@Override
	public String toString() {
        return "(mult " + lhs.getASTCode() + " " + rhs.getASTCode() + ")";
    }
}
