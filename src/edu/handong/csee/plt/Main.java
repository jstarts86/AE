package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.faevalue.RBMRFAEValue;
import edu.handong.csee.plt.faevalue.NumV;
import edu.handong.csee.plt.store.Store;

import java.util.ArrayList;

public class Main {
	
	static boolean onlyParser = false; // for -p option
	
	/* Test code
	{with {a 3} {setvar a 5}}
	{with {x 3} {seqn {{fun {x} {setvar x 5}} x} x}}
	{with {a 3} {seqn {{fun {x} {setvar x 5}} a} a}}
	{with {swap {fun {x} {fun {y} {with {z x} {seqn {setvar x y} {setvar y z}}}}}} {with {a 10} {with {b 20} {seqn {{swap a} b} a}}}}

	{rec {count {fun {n} {if0 n 0 {+ 1 {count {- n 1}}}}}} {count 8}}
	 */

	public static void main(String[] args) {
		
//		if(args.length == 0) {
//			System.out.println("No code provided!");
//			return;
//		}
		String exampleCode = "{rec {count {fun {n} {if0 n 0 {+ 1 {count {- n 1}}}}}} {count 8}}";
//		if(args[0].equals("-p")) {
//			onlyParser = true;
//			exampleCode = args[1];
//		}
//		else {
//			exampleCode = args[0];
//		}
//		if (exampleCode.isEmpty()) {
//			System.out.println("No code :////");
//			return;
//		}
		Parser parser = new Parser();
		ArrayList<String> hi = parser.splitExpressionAsSubExpressions(exampleCode);

		AST ast = parser.parse(exampleCode);
//		if(ast == null)
//			System.out.println("Syntax Error!");
//
//		if(onlyParser)
//			System.out.println(ast.getASTCode());
		
		// interpreter
		// else {
			System.out.println(exampleCode);
			System.out.println(ast.getASTCode());
			DefrdSub defSub = new DefrdSub();
			Store store = new Store();
			ValueStore result = Interpreter.interp(ast, defSub, store);
			System.out.println(result.getValueStoreCode());

		//}
	}
}