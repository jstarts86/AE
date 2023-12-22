package edu.handong.csee.plt.faevalue;
import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defsub.DefrdSub;

public class RefclosV extends RBMRFAEValue {
    String param;
    AST body;

    DefrdSub ds;

    public RefclosV(String param, AST body, DefrdSub ds) {
        this.param = param;
        this.body = body;
        this.ds = ds;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public AST getBody() {
        return body;
    }

    public void setBody(AST body) {
        this.body = body;
    }

    public DefrdSub getDs() {
        return ds;
    }

    public void setDs(DefrdSub ds) {
        this.ds = ds;
    }

    public String getFAEValueCode() {
        return "(refclosV " + getParam() + " " + getBody() + " " + getDs() + ")";
    }
    @Override
    public String toString() {
        return "(refclosV " + getParam() + " " + getBody() + " " + getDs() + ")";
    }
}
