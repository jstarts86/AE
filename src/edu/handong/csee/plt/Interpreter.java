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
	public interface NumOp {
		int operation(int a, int b);
	}
	public static FAEValue operateOp(FAEValue a, FAEValue b, NumOp op) {
		NumV a_a = (NumV) a;
		NumV b_b = (NumV) b;
		int total = op.operation(Integer.parseInt(a_a.getNum()), Integer.parseInt(b_b.getNum()));
		String s = Integer.toString(total);
		return new NumV(s);
	}







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

	public FAEValue interp(AST ast, DefrdSub ds) {
		
		if(ast instanceof Num) {
			Num num = (Num) ast;
			NumV result = new NumV(num.getStrNum());
			return result;
		}


		if(ast instanceof Add) {
			Add add = (Add)ast;
			NumOp addition = (a,b) -> a + b;
			return Interpreter.operateOp(interp(add.getLhs(), ds), interp(add.getRhs(), ds), addition);
		}
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast;
			NumOp subtraction = (a, b) -> a - b;
			return Interpreter.operateOp(interp(sub.getLhs(), ds), interp(sub.getRhs(), ds), subtraction);
		}

		if(ast instanceof Id) {
			Id id = (Id) ast;
			return lookup(id.getName(), ds);
//			return "" + (Integer.parseInt((numV) fae , ds));
		}
		if(ast instanceof Fun) {
			Fun fun  = (Fun) ast;
			return new ClosureV(fun.getParam(), fun.getBody(), ds);
		}
		if(ast instanceof App) {
			App app = (App) ast;
			ClosureV f_val = (ClosureV) interp(app.getFunExpr(), ds);
			ClosureV a_val = (ClosureV) interp(app.getArgExpr(), ds);
			ASub cache = new ASub(f_val.getParam(), a_val, f_val.getDs());
			return interp(f_val.getBody(), cache);
		}
		return null;
	}
}
