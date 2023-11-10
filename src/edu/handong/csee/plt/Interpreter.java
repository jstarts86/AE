package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.*;
import edu.handong.csee.plt.defsub.ASub;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.defsub.EmptySub;
import edu.handong.csee.plt.faevalue.ClosureV;
import edu.handong.csee.plt.faevalue.FAEValue;
import edu.handong.csee.plt.faevalue.NumV;


import java.util.Objects;

public class Interpreter {
	public FAEValue lookup (String name, DefrdSub ds) {
		if(ds instanceof EmptySub) {
			System.out.println("Lookup Free identifier ");
		}
		if(ds instanceof ASub) {
			if(Objects.equals(name, ((ASub) ds).getName())) {
				return ((ASub)ds).getValue();
			}
			else {
				return lookup( name , ((ASub) ds).ds);
			}
		}
		return null;
	}

	public String interp(AST ast, DefrdSub ds) {
		
		if(ast instanceof Num) {
			return ((Num)ast).getStrNum();
		}


		if(ast instanceof Add) {
			Add add = (Add)ast;
			return "" + (Integer.parseInt(interp(add.getLhs(), ds)) + Integer.parseInt(interp(add.getRhs(), ds)));
		}
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast;
			return "" + (Integer.parseInt(interp(sub.getLhs(), ds)) - Integer.parseInt(interp(sub.getRhs(), ds)));
		}

		if(ast instanceof Id) {
			Id id = (Id) ast;
			FAEValue fae = lookup(id.getName(), ds);
			return ((NumV)fae).getNum().toString();
//			return "" + (Integer.parseInt((numV) fae , ds));
		}
		if(ast instanceof Fun) {
			Fun fun  = (Fun) ast;
			return "" + new ClosureV(fun.getParam(), fun.getBody(), ds);
		}
		if(ast instanceof App) {
//			App app = (App) ast;
//			ClosureV f_val = new ClosureV()


		}
		return null;
	}
}
