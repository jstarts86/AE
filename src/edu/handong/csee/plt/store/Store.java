package edu.handong.csee.plt.store;

import edu.handong.csee.plt.defsub.ASub;

public class Store {

    public String getStoreCode() {
        String storeCode = "";

        if(this instanceof EmptySto)
            storeCode = ((EmptySto)this).getStoreCode();

        if(this instanceof ASto)
            storeCode = ((ASto)this).getStoreCode();
        return storeCode;
    }
    @Override
        public String toString() {
        String storeCode = "";

        if(this instanceof EmptySto)
            storeCode = ((EmptySto)this).getStoreCode();

        if(this instanceof ASto)
            storeCode = ((ASto)this).getStoreCode();
        return storeCode;
    }

}
