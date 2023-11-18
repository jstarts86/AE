package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.rcfaevalue.RCFAEValue;
import edu.handong.csee.plt.rcfaevalue.NumV;

import java.util.ArrayList;

public class Main {
	
	static boolean onlyParser = false; // for -p option
	//test cases
	//String exampleCode = "{{fun {x} {+ x x}} {+ 3 3}}";
	//String exampleCode = "{with {y 4} {fun {x} {+ x x}} {+ y 3}}";
	//String exampleCode = "{with {x 3} {+ x x}}";
	//String exampleCode = "{{+ x x}}}";
	//String exampleCode = "{with {z {fun {x} {+ x y}}} {with {y 10} z}}";
	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.out.println("No code provided!");
			return;
		}
		String exampleCode = "";
		if(args[0].equals("-p")) {
			onlyParser = true;
			exampleCode = args[1];
		}
		else {
			exampleCode = args[0];
		}
		if (exampleCode.isEmpty()) {
			System.out.println("No code :////");
			return;
		}
		Parser parser = new Parser();
		ArrayList<String> hi = parser.splitExpressionAsSubExpressions(exampleCode);

		AST ast = parser.parse(exampleCode);
		if(ast == null)
			System.out.println("Syntax Error!");
		
		if(onlyParser)
			System.out.println(ast.getASTCode());
		
		// interpreter
		else {
			Interpreter interpreter = new Interpreter();
			DefrdSub defSub = new DefrdSub();
			RCFAEValue result = interpreter.interp(ast, defSub);
			if(result instanceof NumV num) {
				String num_result = num.getNum();
				System.out.println(num_result);
			}
			else {
				System.out.println(result.getRCFAEValueCode());
			}
		}


	}
}