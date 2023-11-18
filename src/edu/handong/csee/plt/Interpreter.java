package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.*;
import edu.handong.csee.plt.defsub.ARecSub;
import edu.handong.csee.plt.defsub.ASub;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.defsub.EmptySub;
import edu.handong.csee.plt.rcfaevalue.ClosureV;
import edu.handong.csee.plt.rcfaevalue.ExpressionV;
import edu.handong.csee.plt.rcfaevalue.RCFAEValue;
import edu.handong.csee.plt.rcfaevalue.NumV;
import java.util.Objects;



public class Interpreter {
	public interface NumOp {
		int operation(int a, int b);
	}
	public static Boolean numZero(RCFAEValue n) {
		NumV n_NumV = (NumV) n;
		int number = Integer.parseInt(n_NumV.getNum());
		if(number == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public static RCFAEValue operateOp(RCFAEValue a, RCFAEValue b, NumOp op) {
		NumV a_a = (NumV) strict(a);
		NumV b_b = (NumV) strict(b);

		int total = op.operation(Integer.parseInt(a_a.getNum()), Integer.parseInt(b_b.getNum()));

		String s = Integer.toString(total);

		return new NumV(s);
	}
	public static RCFAEValue strict(RCFAEValue input) {
		if(input instanceof ExpressionV) {
			ExpressionV e_input = ((ExpressionV) input);
			if(e_input.getValueBox().getValue() == null) {
				e_input.getValueBox().setValue(strict(Interpreter.interp(e_input.getExpression(), e_input.getDs())));
				return e_input.getValueBox().getValue();
			} else {
				return ((ExpressionV) input).getValueBox().getValue();
			}
		}
		return input;
	}
	public static RCFAEValue lookup (String name, DefrdSub ds) {
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
		if(ds instanceof ARecSub) {
			ARecSub substitution = ((ARecSub)ds);
			if(name.equals(substitution.getName())) {
				return substitution.getValueBox().getValue();
			} else {
				return lookup(name, substitution.getDs());
			}
		}

		return null;
	}

	public static RCFAEValue interp(AST ast, DefrdSub ds) {
		//Num
		Interpreter interpreter = new Interpreter();
		if(ast instanceof Num) {
			Num num = (Num) ast;
			NumV result = new NumV(num.getStrNum());
			return result;
		}
		//Add
		if(ast instanceof Add) {
			Add add = (Add)ast;
			NumOp addition = (a,b) -> a + b;
			return Interpreter.operateOp(interp(add.getLhs(), ds), interp(add.getRhs(), ds), addition);
		}
		//Sub
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast;
			NumOp subtraction = (a, b) -> a - b;
			return Interpreter.operateOp(interp(sub.getLhs(), ds), interp(sub.getRhs(), ds), subtraction);
		}
		if(ast instanceof Mult) {
			Mult mult = (Mult)ast;
			NumOp multiplication = (a, b) -> a * b;
			return Interpreter.operateOp(interp(mult.getLhs(), ds), interp(mult.getRhs(), ds), multiplication);
		}
		//Id
		if(ast instanceof Id) {
			Id id = (Id) ast;
			return Interpreter.lookup(id.getName(), ds);
		}
		//Fun
		if(ast instanceof Fun) {
			Fun fun  = (Fun) ast;
			return new ClosureV(fun.getParam(), fun.getBody(), ds);
		}
		//App
		if(ast instanceof App) {
			App app = (App) ast;
			RCFAEValue f_val = interp(app.getFunExpr(), ds);
			RCFAEValue a_val = interp(app.getArgExpr(), ds);
			ClosureV closure = (ClosureV) f_val;
			ASub cache = new ASub(closure.getParam(), a_val, closure.getDs());
			return interp(closure.getBody(), cache);
		}
		if(ast instanceof IfZero) {
			IfZero ifzero = (IfZero) ast;
			if (numZero(Interpreter.interp(ifzero.getTestExpression(), ds))) {
				return interp(ifzero.getThenExpression(),ds);
			} else {
				return interp(ifzero.getElseExpression(),ds);
			}

		}
		if(ast instanceof Rec) {
			Rec rec = (Rec) ast;
			Box<RCFAEValue> valueHolder = new Box<RCFAEValue>(new NumV("198"));
			ARecSub newDS = new ARecSub(rec.getName(), valueHolder, ds);
			valueHolder.setValue(interp(rec.getExpression(),newDS));
			return interp(rec.getFstCall(),newDS);
		}

		return null;
	}
}