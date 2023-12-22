package edu.handong.csee.plt.faevalue;

public class BoxV extends RBMRFAEValue {

    int address;

    public BoxV(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getFAEValueCode() {
        return "(boxV " + getAddress()  + ")";
    }
    @Override
    public String toString() {
        return "(boxV " + getAddress()  + ")";
    }
}
