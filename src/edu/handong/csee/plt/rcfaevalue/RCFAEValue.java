package edu.handong.csee.plt.rcfaevalue;

public class RCFAEValue {

    public String getRCFAEValueCode() {
        String faeCode = "";

        if(this instanceof NumV)
            faeCode = ((NumV)this).getRCFAEValueCode();
        if(this instanceof ClosureV)
            faeCode = ((ClosureV)this).getRCFAEValueCode();
        if(this instanceof ExpressionV)
            faeCode = ((ExpressionV)this).getRCFAEValueCode();
        return faeCode;
    }
}
