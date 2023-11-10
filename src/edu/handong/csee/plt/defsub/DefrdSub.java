package edu.handong.csee.plt.defsub;

import edu.handong.csee.plt.faevalue.NumV;

public class DefrdSub {
    public String getDefrdSubCode() {
        String defSubCode = "";

        if(this instanceof EmptySub)
            defSubCode = ((EmptySub)this).getDefrdSubCode();
        if(this instanceof ASub)
            defSubCode = ((ASub)this).getDefrdSubCode();
        return defSubCode;
    }

}
