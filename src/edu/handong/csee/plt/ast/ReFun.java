package edu.handong.csee.plt.ast;

public class ReFun extends AST{
    String param;
    AST body;

    public ReFun(String param, AST body) {
        this.param = param;
        this.body = body;
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

    public String getAstCode() {
        return "(refun " + param + " " + body.getASTCode() + ")";
    }
    @Override
    public String toString() {
        return "(refun " + param + " " + body.toString() + ")";
    }

}


