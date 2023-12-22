package edu.handong.csee.plt.store;

import edu.handong.csee.plt.faevalue.RBMRFAEValue;

public class ASto extends Store{
    int address;
    RBMRFAEValue value;

    Store rest;


    public ASto(int address, RBMRFAEValue value, Store rest) {
        this.address = address;
        this.value = value;
        this.rest = rest;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public RBMRFAEValue getValue() {
        return value;
    }

    public void setValue(RBMRFAEValue value) {
        this.value = value;
    }

    public Store getRest() {
        return rest;
    }

    public void setRest(Store rest) {
        this.rest = rest;
    }

    public String getStoreCode() {
        return "(aSto " + getAddress() + " " + getValue() + " " + getRest() + ")";
    }

    @Override
    public String toString() {
        return "(aSto " + getAddress() + " " + getValue() + " " + getRest() + ")";
    }
}
