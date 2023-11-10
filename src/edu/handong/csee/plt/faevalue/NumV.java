package edu.handong.csee.plt.faevalue;

import edu.handong.csee.plt.ast.Num;

public class NumV extends FAEValue{
    Num num;

    public NumV(Num num){
        this.num = num;
    }

    public Num getNum() {
        return num;
    }
    public String getFAEValueCode() {
        return "(numV " + getNum() + ")";
    }

}
