package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.*;
import edu.handong.csee.plt.defsub.ARecSub;
import edu.handong.csee.plt.defsub.ASub;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.defsub.EmptySub;
import edu.handong.csee.plt.faevalue.*;
import edu.handong.csee.plt.store.*;

import javax.swing.*;
import java.util.Objects;



public class Interpreter {
	public interface NumOp {
		int operation(int a, int b);
	}
	public static RBMRFAEValue strict(RBMRFAEValue input) {
		if(input instanceof ExpressionV) {
			ExpressionV e_input = ((ExpressionV) input);
			if(e_input.getValueBox().getValue() == null) {
				EmptySto sto = new EmptySto();
				RBMRFAEValue strictValue = Interpreter.interp(e_input.getExpression(), e_input.getDs(), sto).getValue();
				strictValue = strict(strictValue);
				NumV strictNumV = (NumV) strictValue;
				Num extractedNum = new Num(strictNumV.getNum());
				e_input.getValueBox().setValue(extractedNum);
				return strictValue;
			} else {
				Num evaluatedNum = (Num) e_input.getValueBox().getValue();
				return new NumV(evaluatedNum.getStrNum());
			}
		}
		return input;
	}
	public static RBMRFAEValue operateOp(RBMRFAEValue a, RBMRFAEValue b, NumOp op) {
		a = strict(a);
		b = strict(b);
		NumV a_a = (NumV) a;
		NumV b_b = (NumV) b;
		int total = op.operation(Integer.parseInt(a_a.getNum()), Integer.parseInt(b_b.getNum()));
		String s = Integer.toString(total);
		return new NumV(s);
	}
	public static int lookup(String name, DefrdSub ds) {
		if(ds instanceof EmptySub) {
			System.out.println("Lookup Free identifier ");
		}
		if(ds instanceof ASub) {
			if(Objects.equals(name, ((ASub) ds).getName())) {
				return ((ASub)ds).getAddress();
			}
			else {
				return lookup(name , ((ASub) ds).ds);
			}
		}
		if(ds instanceof ARecSub) {
			ARecSub sub = (ARecSub) ds;
			if(Objects.equals(name, ((ARecSub) ds).getName())) {
				Num innerValue = (Num) sub.getValueBox().getValue();
				return Integer.parseInt(innerValue.getStrNum());
			}
		}
		return 0;
	}

	public static RBMRFAEValue storeLookup(int address, Store sto) {
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

	public static int maxAddress(Store sto) {
		if(sto instanceof EmptySto) {
			return 0;
		}
		if(sto instanceof ASto) {
			return Integer.max(((ASto) sto).getAddress(), maxAddress(((ASto) sto).getRest()));
		}
		return 0;
	}
	public static int malloc(Store sto) {
		return 1 + maxAddress(sto);
	}

	public static Boolean numZero(RBMRFAEValue n) {
		NumV number = (NumV) n;
		int numberInt = Integer.getInteger(number.getNum());
        return numberInt == 0;
	}

	public static ValueStore interp(AST ast, DefrdSub ds, Store st) {
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
			return new ValueStore(storeLookup(lookup(id.getName(), ds), st), st);
		}
		//Fun
		if(ast instanceof Fun) {
			Fun fun  = (Fun) ast;
			return new ValueStore(new ClosureV(fun.getParam(), fun.getBody(), ds), st);
		}
		if(ast instanceof ReFun) {
			ReFun refun = (ReFun) ast;
			return new ValueStore(new RefclosV(refun.getParam(), refun.getBody(), ds), st);
		}

		//App
		if(ast instanceof App) {
			App app = (App) ast;
			if(interp(app.getFunExpr(), ds, st) instanceof ValueStore) {
				ValueStore function = interp(app.getFunExpr(), ds, st);
				if(function.getValue() instanceof ClosureV) {
					ClosureV closureV = (ClosureV) function.getValue();
					if(interp(app.getArgExpr(), ds, function.getStore()) instanceof ValueStore){
						ValueStore argument = interp(app.getArgExpr(), ds, function.getStore());
						int newAddress = malloc(argument.getStore());
						return interp(closureV.getBody(), new ASub(closureV.getParam(), newAddress, closureV.getDs()), new ASto(newAddress, argument.getValue(), argument.getStore()));
					}
				}
				if (function.getValue() instanceof RefclosV) {
					RefclosV refclosV = (RefclosV) function.getValue();
					if(interp(app.getArgExpr(), ds, function.getStore()) instanceof ValueStore) {
						ValueStore argument = interp(app.getArgExpr(), ds, function.getStore());
						Id id = (Id) app.getArgExpr();
						int address = lookup(id.getName(), ds);
						return interp(refclosV.getBody(), new ASub(refclosV.getParam(), address, refclosV.getDs()), new ASto(address, argument.getValue(), argument.getStore()));
					}
				}
				else {
					System.out.println("trying to apply a number");
				}
			}
//			RBMRFAEValue f_val = interp(app.getFunExpr(), ds);
//			RBMRFAEValue a_val = interp(app.getArgExpr(), ds);
//			ClosureV closure = (ClosureV) f_val;
//			ASub cache = new ASub(closure.getParam(), a_val, closure.getDs());
//			return interp(closure.getBody(), cache);

		}
		if(ast instanceof SetVar) {
			SetVar setVar = (SetVar) ast;
			int a = lookup(setVar.getName(), ds);
			if(interp(setVar.getValue(), ds, st) instanceof ValueStore) {
				ValueStore something = interp(setVar.getValue(), ds, st);
				return new ValueStore(something.getValue(), new ASto(a, something.getValue(), st));
			}
		}
		if(ast instanceof NewBox) {
			NewBox newBox = (NewBox) ast;
			if (interp(newBox.getValue(),ds ,st) instanceof ValueStore) {
				ValueStore box1 = interp(newBox.getValue(),ds ,st);
				int a = malloc(box1.getStore());
				return new ValueStore(new BoxV(a), new ASto(a, box1.getValue(), box1.getStore()));
			}
		}
		if(ast instanceof SetBox) {
			SetBox setBox = (SetBox) ast;
			if(interp(setBox.getBoxName(), ds,st) instanceof ValueStore){
				ValueStore box1 = interp(setBox.getBoxName(), ds,st);
				if(interp(setBox.getValue(), ds, st) instanceof ValueStore) {
					ValueStore box2 = interp(setBox.getValue(), ds, st);
					BoxV boxV = (BoxV) box1.getValue();
					return new ValueStore(box2.getValue(), new ASto(boxV.getAddress(),box2.getValue(), box2.getStore()));
				}
			}
		}
		if(ast instanceof OpenBox) {
			OpenBox openBox = (OpenBox) ast;
			if(interp(openBox.getValue(), ds, st) instanceof ValueStore) {
				ValueStore box1 =interp(openBox.getValue(), ds, st);
				BoxV boxV = (BoxV) box1.getValue();
				return new ValueStore(storeLookup(boxV.getAddress(), box1.getStore()), box1.getStore());
			}
		}
		if(ast instanceof Seqn) {
			Seqn seqn = (Seqn) ast;
			if(interp(seqn.getEx1(),ds ,st) instanceof ValueStore) {
				ValueStore a =interp(seqn.getEx1(),ds ,st);
				return interp(seqn.getEx2(), ds, a.getStore());
			}
		}
		if (ast instanceof IfZero) {
			IfZero ifZero = (IfZero) ast;
			ValueStore vs = interp(ifZero.getTestExpression(), ds, st);
			RBMRFAEValue seperatedValue = vs.getValue();
			if (numZero(seperatedValue)) {
				return interp(ifZero.getThenExpression(), ds, st);
			}
			else {
				return interp(ifZero.getElseExpression(), ds, st);
			}
		}
		if (ast instanceof Rec) {
		   Rec rec = (Rec) ast;
			ValueStore valueHolder = new ValueStore(new NumV("198"), st);
			NumV boxContent =  (NumV) valueHolder.getValue();
			Num numbers = new Num(boxContent.getNum());
			NewBox boxes = new NewBox(numbers);
			DefrdSub newDS = new ARecSub(rec.getName(), boxes, ds);

			ValueStore evaluatedNamedExpr = interp(rec.getExpression(), newDS, st);
			valueHolder.setValue(evaluatedNamedExpr.getValue());


			return interp(rec.getFunctionCall(), newDS, st);
		}

		return null;
	}
}