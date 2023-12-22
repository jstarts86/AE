package edu.handong.csee.plt.faevalue;

public class NumV extends RBMRFAEValue {
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
    @Override
    public String toString() {
        return "(numV " + getNum() + ")";
    }

}
