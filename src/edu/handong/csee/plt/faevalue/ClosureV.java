package edu.handong.csee.plt.faevalue;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.defsub.EmptySub;

public class ClosureV extends FAEValue{
    String param;
    AST body;
    DefrdSub ds;

    public ClosureV(String param, AST body, DefrdSub ds) {
        this.param = param;
        this.body = body;
        this.ds = ds;
    }
    public String getParam() {
        return param;
    }

    public AST getBody() {
        return body;
    }

    public DefrdSub getDs() {
        return ds;
    }
    public String getFAEValueCode() {
        return "(closureV " + getParam() + " " + getBody() + " " + getDs() + ")";
    }
    @Override
    public String toString() {
        return "(closureV " + getParam() + " " + getBody() + " " + getDs() + ")";
    }

}
