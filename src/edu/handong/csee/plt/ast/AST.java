package edu.handong.csee.plt.ast;

public class AST {
	
	public String getASTCode() {
		String astCode="";
		if(this instanceof Add)
			astCode = ((Add)this).getASTCode();
		
		if(this instanceof Num)
			astCode = ((Num)this).getASTCode();

		if(this instanceof Sub)
			astCode = ((Sub)this).getASTCode();

		if(this instanceof Id)
			astCode = ((Id)this).getASTCode();
		if(this instanceof Fun)
			astCode = ((Fun)this).getASTCode();
		if(this instanceof App)
			astCode = ((App)this).getASTCode();
		if(this instanceof NewBox)
			astCode = ((NewBox)this).getASTCode();
		if(this instanceof OpenBox)
			astCode = ((OpenBox)this).getASTCode();
		if(this instanceof ReFun)
			astCode = ((ReFun)this).getASTCode();
		if(this instanceof Seqn)
			astCode = ((Seqn)this).getASTCode();
		if(this instanceof SetBox)
			astCode = ((SetBox)this).getASTCode();
		if(this instanceof SetVar)
			astCode = ((SetVar)this).getASTCode();
		return astCode;
	}
}

