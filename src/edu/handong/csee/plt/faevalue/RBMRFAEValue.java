package edu.handong.csee.plt.faevalue;

public class RBMRFAEValue {

    public String getFAEValueCode() {
        String faeCode = "";

        if(this instanceof NumV)
            faeCode = ((NumV)this).getFAEValueCode();
        if(this instanceof ClosureV)
            faeCode = ((ClosureV)this).getFAEValueCode();
        if(this instanceof RefclosV)
            faeCode = ((RefclosV)this).getFAEValueCode();
        if(this instanceof BoxV)
            faeCode = ((BoxV)this).getFAEValueCode();
        return faeCode;
    }


}
