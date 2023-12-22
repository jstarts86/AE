package edu.handong.csee.plt.ast;

public class SetBox extends AST{
    AST boxName;
    AST value;

    public SetBox(AST boxName, AST value) {
        this.boxName = boxName;
        this.value = value;
    }

    public AST getBoxName() {
        return boxName;
    }

    public void setBoxName(AST boxName) {
        this.boxName = boxName;
    }

    public AST getValue() {
        return value;
    }

    public void setValue(AST value) {
        this.value = value;
    }

    public String getASTCode() {
        return "(setbox " + getBoxName().getASTCode() + " " + getValue().getASTCode() + ")";
    }
    @Override
    public String toString() {
        return "(setbox " + getBoxName().toString() + " " + getValue().toString() + ")";
    }
}
