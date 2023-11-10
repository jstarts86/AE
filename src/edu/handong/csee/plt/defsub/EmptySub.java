package edu.handong.csee.plt.defsub;

public class EmptySub extends DefrdSub{
    String mt = "mtSub";

    public EmptySub() {
        this.mt = "mtSub";
    }
    public EmptySub(String ds) {
        this.mt = "mtSub";
    }

    public String getMtSub() {
        return mt;
    }
    public String getDefrdSubCode() {
        return getMtSub();
    }
    @Override
    public String toString() {
        return "(" + getMtSub() + ")";
    }
}
