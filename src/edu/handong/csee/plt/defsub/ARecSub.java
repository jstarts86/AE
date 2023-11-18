package edu.handong.csee.plt.defsub;

import edu.handong.csee.plt.Box;
import edu.handong.csee.plt.rcfaevalue.RCFAEValue;

public class ARecSub extends DefrdSub {
    String name;
    Box<RCFAEValue> valueBox;
    DefrdSub ds;

    public ARecSub(String name, Box<RCFAEValue> valueBox, DefrdSub ds) {
        this.name = name;
        this.valueBox = valueBox;
        this.ds = ds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Box<RCFAEValue> getValueBox() {
        return valueBox;
    }

    public void setValueBox(Box<RCFAEValue> valueBox) {
        this.valueBox = valueBox;
    }

    public DefrdSub getDs() {
        return ds;
    }

    public void setDs(DefrdSub ds) {
        this.ds = ds;
    }

    @Override
    public String getDefrdSubCode() {
        return "(aRecSub " + getName() + " " + getValueBox() + getDs() + ")";
    }
    public String toString() {
        return "(aRecSub " + getName() + " " + getValueBox() + getDs() + ")";
    }

}
