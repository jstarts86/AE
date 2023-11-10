package edu.handong.csee.plt.faevalue;

import edu.handong.csee.plt.ast.Num;

public class NumV extends FAEValue{
    String num = "";

    public NumV(String num){
        this.num = num;
    }

    public String getNum() {
        return num;
    }
    public String getFAEValueCode() {
        return "(numV " + getNum() + ")";
    }

}
