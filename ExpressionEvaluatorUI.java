package eg.edu.alexu.csd.datastructure.stack.cs34;

import java.util.Scanner;

/**
 * ExpressionEvaluator UI for getting the input from the user in infix notation then change it postfix and printing the expression with its value
 * @author cs34
 *
 */
public class ExpressionEvaluatorUI {

	/**
	 * main method for the application
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExpressionEvaluator evaluate = new ExpressionEvaluator();
		Scanner scan = new Scanner(System.in);
		/**
		 * indicates when the input ends
		 */
		scan.useDelimiter("\r\n");
		System.out.println("Enter the infix expression: ");
		String infix = scan.next();
		String number;
		String postfix = evaluate.infixToPostfix(infix);
		/**
		 * infix expression needs to be valid
		 */
		while (postfix == null) {
			System.out.println("Invalid expression");
			System.out.println("Enter valid infix expression: ");
			infix = scan.next();
			postfix = evaluate.infixToPostfix(infix);
		}
		/**
		 * new string for evaluating the non-numeric expressions
		 */
		String numericPostfix = postfix;
		for ( int i = 0 ; i < numericPostfix.length() ; i++) {
			if (Character.isLetter(numericPostfix.charAt(i))) {
				System.out.print("The value of " + numericPostfix.charAt(i) + " = ");
				/**
				 * value must be an integer to continue
				 */
				while (!scan.hasNextInt()) {
					System.out.println("Enter valid value");
					System.out.print("The value of " + numericPostfix.charAt(i) + " = ");
					number = scan.next();
				}
				number = scan.next();
				/**
				 * replacing all occurrences of this symbol with its value
				 */
				numericPostfix = numericPostfix.replaceAll(numericPostfix.substring(i, i+1), number);
			}
		}
		System.out.println("The postfix expression is " + postfix + " and its value is " + evaluate.evaluate(numericPostfix));
		scan.close();
	}

}
