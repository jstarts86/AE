package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.*;
import edu.handong.csee.plt.defsub.ASub;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.defsub.EmptySub;
import edu.handong.csee.plt.faevalue.ClosureV;
import edu.handong.csee.plt.faevalue.RBMRFAEValue;
import edu.handong.csee.plt.faevalue.NumV;
import edu.handong.csee.plt.store.*;

import java.util.Objects;



public class Interpreter {
	public interface NumOp {
		int operation(int a, int b);
	}
	public static RBMRFAEValue operateOp(RBMRFAEValue a, RBMRFAEValue b, NumOp op) {
		NumV a_a = (NumV) a;
		NumV b_b = (NumV) b;
		int total = op.operation(Integer.parseInt(a_a.getNum()), Integer.parseInt(b_b.getNum()));
		String s = Integer.toString(total);
		return new NumV(s);
	}
	public RBMRFAEValue lookup (String name, DefrdSub ds) {
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

	public RBMRFAEValue storeLookup(int address, Store sto) {
		if(sto instanceof EmptySto) {
			System.out.println("No value at address");
		}
		if(sto instanceof ASto) {
			if(address == (((ASto) sto).getAddress())) {
				return ((ASto) sto).getValue();
			}
			else {
				return storeLookup(address, ((ASto) sto).getRest());
			}
		}
		return null;
	}

	public int maxAddress(Store sto) {
		if(sto instanceof EmptySto) {
			return 0;
		}
		if(sto instanceof ASto) {
			return Integer.max(((ASto) sto).getAddress(), maxAddress(sto));
		}
		return 0;
	}
	public int malloc(Store sto) {
		return 1 + maxAddress(sto);
	}

	public ValueStore interp(AST ast, DefrdSub ds, Store st) {
		//Num
		if(ast instanceof Num) {
			Num num = (Num) ast;
			NumV result = new NumV(num.getStrNum());


			return new ValueStore(result, st);
		}
		//Add
		if(ast instanceof Add) {
			Add add = (Add)ast;
			NumOp addition = (a,b) -> a + b;
			//return Interpreter.operateOp(interp(add.getLhs(), ds), interp(add.getRhs(), ds), addition);
			if(interp(add.getLhs(), ds, st) instanceof ValueStore) {
				ValueStore leftHand = (ValueStore)interp(add.getLhs(), ds, st);
				if(interp(add.getRhs(), ds , leftHand.getStore()) instanceof ValueStore) {
					ValueStore rightHand = (ValueStore)interp(add.getRhs(), ds, leftHand.getStore());
					return new ValueStore(Interpreter.operateOp(leftHand.getValue(), rightHand.getValue(), addition), rightHand.getStore());
				}
			}
		}
		//Sub
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast;
			NumOp subtraction = (a, b) -> a - b;
//			return Interpreter.operateOp(interp(sub.getLhs(), ds), interp(sub.getRhs(), ds), subtraction);
			if(interp(sub.getLhs(), ds, st) instanceof ValueStore) {
				ValueStore leftHand = (ValueStore)interp(sub.getLhs(), ds, st);
				if(interp(sub.getRhs(), ds , leftHand.getStore()) instanceof ValueStore) {
					ValueStore rightHand = (ValueStore)interp(sub.getRhs(), ds, leftHand.getStore());
					return new ValueStore(Interpreter.operateOp(leftHand.getValue(), rightHand.getValue(), subtraction), rightHand.getStore());
				}
			}
		}
		//Id
		if(ast instanceof Id) {
			Id id = (Id) ast;
			return lookup(id.getName(), ds);
		}
		//Fun
		if(ast instanceof Fun) {
			Fun fun  = (Fun) ast;
			return new ClosureV(fun.getParam(), fun.getBody(), ds);
		}
		//App
		if(ast instanceof App) {
			App app = (App) ast;
			RBMRFAEValue f_val = interp(app.getFunExpr(), ds);
			RBMRFAEValue a_val = interp(app.getArgExpr(), ds);
			ClosureV closure = (ClosureV) f_val;
			ASub cache = new ASub(closure.getParam(), a_val, closure.getDs());
			return interp(closure.getBody(), cache);
		}
		return null;
	}
}