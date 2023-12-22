package edu.handong.csee.plt.ast;

public class NewBox extends AST {
    AST value;

    public NewBox(AST value) {
        this.value = value;
    }

    public AST getValue() {
        return value;
    }

    public void setValue(AST value) {
        this.value = value;
    }

    public String getASTCode() {
        return "(newbox " + getValue().getASTCode() + ")";
    }
    @Override
    public String toString() {
        return "(newbox " + getValue().toString() + ")";
    }
}
