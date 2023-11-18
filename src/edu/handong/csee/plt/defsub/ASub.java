package edu.handong.csee.plt.defsub;

import edu.handong.csee.plt.faevalue.FAEValue;

public class ASub extends DefrdSub {
    public String name = "";
    public FAEValue value = new FAEValue();
    public DefrdSub ds = new DefrdSub();

    public ASub(String name, FAEValue value, DefrdSub ds) {
        this.name = name;
        this.value = value;
        this.ds = ds;
    }
    public String getName() {
        return name;
    }
    public FAEValue getValue() {
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
