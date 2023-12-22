package edu.handong.csee.plt.ast;

public class OpenBox extends AST {

    AST value;

    public OpenBox(AST value) {
        this.value = value;
    }

    public AST getValue() {
        return value;
    }

    public void setValue(AST value) {
        this.value = value;
    }
    public String getASTCode() {
        return "(openbox " + getValue().getASTCode() + ")";
    }
    @Override
    public String toString() {
        return "(openbox " + getValue().toString() + ")";
    }
}
