package edu.handong.csee.plt;

import edu.handong.csee.plt.faevalue.RBMRFAEValue;
import edu.handong.csee.plt.store.Store;

public class ValueStore {
    RBMRFAEValue value;
    Store store;

    public ValueStore(RBMRFAEValue value, Store store) {
        this.value = value;
        this.store = store;
    }

    public RBMRFAEValue getValue() {
        return value;
    }

    public void setValue(RBMRFAEValue value) {
        this.value = value;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
    public String getValueStoreCode() {
        return "(v*s " + getValue().getFAEValueCode() + " " + getStore().getStoreCode() + ")";
    }

    public String toString() {
        return "(v*s " + getValue().toString() + " " + getStore().toString() + ")";
    }
}
