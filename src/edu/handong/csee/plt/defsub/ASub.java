package edu.handong.csee.plt.defsub;

import edu.handong.csee.plt.faevalue.RBMRFAEValue;

public class ASub extends DefrdSub {
    public String name = "";
    public RBMRFAEValue value = new RBMRFAEValue();
    public DefrdSub ds = new DefrdSub();

    public ASub(String name, RBMRFAEValue value, DefrdSub ds) {
        this.name = name;
        this.value = value;
        this.ds = ds;
    }
    public String getName() {
        return name;
    }
    public RBMRFAEValue getValue() {
        return value;
    }
    public DefrdSub getDs() {
        return ds;
    }

    public String getDefrdSubCode() {
        return "(aSub " + getName() + " " + getValue() + " " + getDs() + ")";
    }
    @Override
    public String toString() {
        return "(aSub " + getName() + " " + getValue() + " " + getDs() + ")";
    }

}
