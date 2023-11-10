package edu.handong.csee.plt;

import java.util.ArrayList;
import java.util.Stack;

import edu.handong.csee.plt.ast.*;

public class Parser {

	AST parse(String exampleCode) {
		ArrayList<String> subExpressions = splitExpressionAsSubExpressions(exampleCode);
//		for(int i = 0; i < subExpressions.size(); i++) {
//			System.out.println("Index " + i + ": " + subExpressions.get(i));
//		}
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
		if (subExpressions.size() == 1  && !isNumeric(subExpressions.get(0))) {
			return new Id((subExpressions.get(0)));
		}
		if (subExpressions.get(0).equals("fun")) {
			ArrayList<String> unwrapped = splitExpressionAsSubExpressions(subExpressions.get(1));
			return new Fun((unwrapped.get(0)), parse(subExpressions.get(2)));
		}
		if (subExpressions.size() == 2) {
			return new App(parse(subExpressions.get(0)), parse(subExpressions.get(1)));
		}
		else if (subExpressions.get(0).equals("with") && subExpressions.size() > 1) {
			// Split the with expression into its parts
			String withExpression = subExpressions.get(1);
			ArrayList<String> identifierValue = splitExpressionAsSubExpressions(withExpression);

			// Ensure there are two parts: i, v
			if (identifierValue.size() != 2) {
				System.out.println("Syntax error in 'with' expression");
				System.exit(0);
			}

			return new App(new Fun(identifierValue.get(0), parse(subExpressions.get(2))), parse(identifierValue.get(1)));
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
