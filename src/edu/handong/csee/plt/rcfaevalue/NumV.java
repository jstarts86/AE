package edu.handong.csee.plt.rcfaevalue;

public class NumV extends RCFAEValue {
    String num = "";

    public NumV(String num){
        this.num = num;
    }

    public String getNum() {
        return num;
    }
    public String getRCFAEValueCode() {
        return "(numV " + getNum() + ")";
    }
    @Override
    public String toString() {
        return "(numV " + getNum() + ")";
    }

}
