package edu.handong.csee.plt;

import java.util.ArrayList;
import java.util.Stack;

import edu.handong.csee.plt.ast.*;

public class Parser {

	AST parse(String exampleCode) {
		ArrayList<String> subExpressions = splitExpressionAsSubExpressions(exampleCode);
		// num
		if (subExpressions.size() == 1 && isNumeric(subExpressions.get(0))) {

			return new Num(subExpressions.get(0));
		}

		// add
		if (subExpressions.get(0).equals("+")) {

			return new Add(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}

		// TODO implement all other cases....

		//sub
		if (subExpressions.get(0).equals("-")) {
			return new Sub(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}
		//id
		if (subExpressions.size() == 1  && !isNumeric(subExpressions.get(0))) {
			return new Id((subExpressions.get(0)));
		}
		//fun
		if (subExpressions.get(0).equals("fun")) {
			ArrayList<String> unwrapped = splitExpressionAsSubExpressions(subExpressions.get(1));
			return new Fun((unwrapped.get(0)), parse(subExpressions.get(2)));
		}
		//App
		if (subExpressions.size() == 2) {
			return new App(parse(subExpressions.get(0)), parse(subExpressions.get(1)));
		}
		//with
		if (subExpressions.get(0).equals("with") && subExpressions.size() > 1) {
			String withExpression = subExpressions.get(1);
			ArrayList<String> identifierValue = splitExpressionAsSubExpressions(withExpression);

			if (identifierValue.size() != 2) {
				System.out.println("Syntax error in 'with' expression");
				System.exit(0);
			}

			return new App(new Fun(identifierValue.get(0), parse(subExpressions.get(2))), parse(identifierValue.get(1)));
		}

		if (subExpressions.get(0).equals("newbox")) {
			return new NewBox(parse(subExpressions.get(1)));
		}
		if (subExpressions.get(0).equals("setbox")) {
			return new SetBox(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}
		if (subExpressions.get(0).equals("openbox")) {
			return new OpenBox(parse(subExpressions.get(1)));
		}
		if (subExpressions.get(0).equals("seqn")) {
			return new Seqn(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}
		if (subExpressions.get(0).equals("refun")) {
			return new ReFun(subExpressions.get(1), parse(subExpressions.get(2)));
		}
		if (subExpressions.get(0).equals("setvar")){
			return new SetVar(subExpressions.get(1), parse(subExpressions.get(2)));
		}
		if (subExpressions.get(0).equals("if0")) {
			return new IfZero(parse(subExpressions.get(1)), parse(subExpressions.get(2)), parse(subExpressions.get(3)));
		}
		if (subExpressions.get(0).equals("rec")) {
			ArrayList<String> unwrapped = splitExpressionAsSubExpressions(subExpressions.get(1));
			return new Rec(unwrapped.get(0), parse(unwrapped.get(1)), parse(subExpressions.get(2)));
		}

		return null;
	}

	public ArrayList<String> splitExpressionAsSubExpressions(String exampleCode) {

		// deal with brackets first.
		if ((exampleCode.startsWith("{") && !exampleCode.endsWith("}"))
				|| (!exampleCode.startsWith("{") && exampleCode.endsWith("}"))) {
			System.out.println("Syntax error");
			System.exit(0);
		}

		if (exampleCode.startsWith("{"))
			exampleCode = exampleCode.substring(1, exampleCode.length() - 1);

		return getSubExpressions(exampleCode);
	}

	/**
	 * This method return a list of sub-expression from the given expression. For
	 * example, {+ 3 {+ 3 4} -> +, 3, {+ 3 4} TODO JC was sleepy while implementing
	 * this method...it has complex logic and might be buggy... You can do better or
	 * find an external library.
	 * 
	 * @param exampleCode
	 * @return list of sub expressions
	 */
	 public ArrayList<String> getSubExpressions(String code) {

		ArrayList<String> subExpressions = new ArrayList<>();
		Stack<Character> stack = new Stack<>();

		String strBuffer = "";

		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) == ' ' && stack.isEmpty()) {
				if (!strBuffer.isEmpty()) {
					subExpressions.add(strBuffer.trim());
					strBuffer = "";
				}
			} else {
				if (code.charAt(i) == '{') {
					stack.add('{');
				} else if (code.charAt(i) == '}' && !stack.isEmpty()) {
					stack.pop();
				}
			}

			strBuffer += code.charAt(i);
		}

		subExpressions.add(strBuffer.trim());

		return subExpressions;

	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

}
