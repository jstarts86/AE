package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defsub.DefrdSub;
import edu.handong.csee.plt.faevalue.FAEValue;
import edu.handong.csee.plt.faevalue.NumV;

import java.util.ArrayList;

public class Main {
	
	static boolean onlyParser = false; // for -p option
	
	public static void main(String[] args) {
		
		// This is just an example code. Use args to get -p option and actual code from CLI
//		String exampleCode = "{+ {+ 2 {+ {+ 4 5} 4}} {+ 1 2}}";
		//String exampleCode = "{{fun {x} {+ x x}} {+ 3 3}}";
		//String exampleCode = "{with {y 4} {fun {x} {+ x x}} {+ y 3}}";
		//String exampleCode = "{with {x 3} {+ x x}}";
		//String exampleCode = "{{+ x x}}}";
		//String exampleCode = "{with {z {fun {x} {+ x y}}} {with {y 10} z}}";
		String exampleCode = "{with {y 10} {fun {x} {+ y x}}}";
		// Parser

		Parser parser = new Parser();
		ArrayList<String> hi = parser.splitExpressionAsSubExpressions(exampleCode);
		for(int i = 0; i < hi.size(); i++) {
			System.out.println("Index " + i + ": " + hi.get(i));
		}
		AST ast = parser.parse(exampleCode);
		if(ast == null)
			System.out.println("Syntax Error!");
		
		//if(onlyParser)
			System.out.println(ast.getASTCode());
		
		// interpreter
		Interpreter interpreter = new Interpreter();
		DefrdSub defSub = new DefrdSub();
		//NumV sum = (NumV) interpreter.interp(ast, defSub);
		//String result = sum.getNum();
		FAEValue result = interpreter.interp(ast, defSub);
		System.out.println(result.getFAEValueCode());

	}
}