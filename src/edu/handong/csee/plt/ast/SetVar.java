package edu.handong.csee.plt.ast;

public class SetVar extends AST {
    String name;
    AST value;

    public SetVar(String name, AST value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AST getValue() {
        return value;
    }

    public void setValue(AST value) {
        this.value = value;
    }

    public String getASTCode(){
        return "{setvar " + getName() + " " + value.getASTCode() + "}";
    }
    @Override
    public String toString(){
        return "{setvar " + getName() + " " + value.toString() + "}";
    }

}
