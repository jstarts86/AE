package edu.handong.csee.plt.faevalue;

import edu.handong.csee.plt.ast.Add;
import edu.handong.csee.plt.ast.Num;
import edu.handong.csee.plt.ast.Sub;

public class FAEValue {

    public String getFAEValueCode() {
        String faeCode = "";

        if(this instanceof NumV)
            faeCode = ((NumV)this).getFAEValueCode();
        if(this instanceof ClosureV)
            faeCode = ((ClosureV)this).getFAEValueCode();

        return faeCode;
    }
}
