package edu.handong.csee.plt.store;

import edu.handong.csee.plt.defsub.ASub;
import edu.handong.csee.plt.defsub.EmptySub;

public class EmptySto extends Store {
    String mtSto = "mtSto";

    public String getMtSto() {
        return "mtSto";
    }

    public void setMtSto(String mtSto) {
        this.mtSto = "mtSto";
    }

    public EmptySto(String mtSto) {
        this.mtSto = "mtSto";
    }

    public String getStoreCode() {
        return "(mtSto)";
    }
    @Override
    public String toString() {
        return "(mtSto)";
    }
}
