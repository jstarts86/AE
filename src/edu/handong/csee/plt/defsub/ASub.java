package edu.handong.csee.plt.defsub;


public class ASub extends DefrdSub {
    public String name = "";
    public int address;
    public DefrdSub ds = new DefrdSub();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public DefrdSub getDs() {
        return ds;
    }

    public void setDs(DefrdSub ds) {
        this.ds = ds;
    }

    public ASub(String name, int address, DefrdSub ds) {
        this.name = name;
        this.address = address;
        this.ds = ds;
    }

    public String getDefrdSubCode() {
        return "(aSub " + getName() + " " + getAddress() + " " + getDs() + ")";
    }
    @Override
    public String toString() {
        return "(aSub " + getName() + " " + getAddress() + " " + getDs() + ")";
    }

}
