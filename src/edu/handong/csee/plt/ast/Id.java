package edu.handong.csee.plt.ast;

public class Id extends AST{
    public Id (String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public String getASTCode() {
        return "(id " + name + ")" ;
    }

    @Override
    public String toString() {
        return "(id " + name + ")" ;
    }


}
