package eg.edu.alexu.csd.datastructure.stack.cs34;

/**
 * transforming the infix notation to postfix notation then evaluating it
 * @author cs34
 */
public class ExpressionEvaluator implements IExpressionEvaluator {
	
	@Override
	public String infixToPostfix(String expression) {
		int i , bracketLCounter = 0 , bracketRCounter = 0 ;
		/**
		 * boolean expression to check if the expression is valid or not
		 */
		boolean operand = false,operator = false,bracketL = false,bracketR = false,space = false, unary = false;
		String postFix = new String();
		Stack operators = new Stack();
		for ( i = 0 ; i < expression.length() ; i++ ) {
			if(Character.isLetterOrDigit(expression.charAt(i))) {
				/**
				 * if the operand is following a right bracket or another operand separated by space then the expression is invalid
				 */
				if (bracketR || space) {
					return null;
				}
				if ((!unary) && (postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ' && !Character.isDigit(postFix.charAt(postFix.length()-1))) {
					postFix += ' ' ;
				}
				postFix += expression.charAt(i);
				operand = true;
				operator = false;
				bracketL = false;
				bracketR = false;
				space = false;
				unary = false;
			}
			else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/') {
				/**
				 * if the operator is following a left bracket directly then the expression is invalid
				 */
				if (bracketL) {
					return null;
				}
				/**
				 * if the first term is * or / then expression is invalid as * and / aren't unary operators
				 */
				if ((expression.charAt(i) == '*' || expression.charAt(i) == '/')&&(postFix.length() == 0)) {
					return null;
				}
				/**
				 * * or / is directly following another operand is invalid
				 */
				if (operator && (expression.charAt(i) == '*' || expression.charAt(i) == '/')) {
					return null;
				}
				/**
				 * unary operators after another operator
				 */
				if (operator && (expression.charAt(i) == '+' || expression.charAt(i) == '-')) {
					if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ' && !unary) {
						postFix += ' ' ;
					}
					else if (expression.charAt(i) == '-') {
						postFix += expression.charAt(i);
					}
					unary = true;
				}
				/**
				 * unary operators at the start of the expression
				 */
				else if (postFix.length() == 0 && operators.isEmpty() && (expression.charAt(i) == '+' || expression.charAt(i) == '-')) {
					if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ' && !unary) {
						postFix += ' ' ;
					}
					else if (expression.charAt(i) == '-') {
						postFix += expression.charAt(i);
					}
					unary = true;
				}
				/**
				 * Conversion between notations
				 */
				else if (operators.isEmpty()) {
					if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ') {
						postFix += ' ' ;
					}
					operators.push(expression.charAt(i));
				}
				else if (((expression.charAt(i) == '+' || expression.charAt(i) == '-')&&((char)operators.peek() == '*' || (char)operators.peek() == '/')) || ((expression.charAt(i) == '+' || expression.charAt(i) == '-')&&((char)operators.peek() == '+' || (char)operators.peek() == '-'))||((expression.charAt(i) == '*' || expression.charAt(i) == '/')&&((char)operators.peek() == '*' || (char)operators.peek() == '/'))) {
					while ((!operators.isEmpty())&&((char)operators.peek() != '(')&&(((expression.charAt(i) == '+' || expression.charAt(i) == '-')&&((char)operators.peek() == '*' || (char)operators.peek() == '/'))||((expression.charAt(i) == '+' || expression.charAt(i) == '-')&&((char)operators.peek() == '+' || (char)operators.peek() == '-'))||((expression.charAt(i) == '*' || expression.charAt(i) == '/')&&((char)operators.peek() == '*' || (char)operators.peek() == '/')))) {
						if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ') {
							postFix += ' ' ;
						}
						postFix += operators.pop();
					}
					operators.push(expression.charAt(i));
				}
				else {
					if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ') {
						postFix += ' ' ;
					}
					operators.push(expression.charAt(i));
				}
				/**
				 * indicating that this term was operator
				 */
				operand = false;
				operator = true;
				bracketL = false;
				bracketR = false;
				space = false;
			}
			else if ((postFix.length() != 0)&&(expression.charAt(i) == ' ')&&(postFix.charAt(postFix.length()-1) != ' ')) {
				postFix += ' ' ;
				/**
				 * indicates that this is the end of operand so it can't be followed by another operand
				 */
				if (operand) {
					space = true;
				}
				else {
					space = false;
				}
				operand = false;
				operator = false;
				bracketL = false;
				bracketR = false;
			}
			else if (expression.charAt(i) == '(') {
				/**
				 * left bracket can't follow right bracket or operand without and operation
				 */
				if (operand || bracketR) {
					return null;
				}
				bracketLCounter ++;
				operators.push(expression.charAt(i));
				operand = false;
				operator = false;
				bracketL = true;
				bracketR = false;
				space = false;
				unary = false;
			}
			/**
			 * right bracket can't follow left bracket or operator without and operand
			 */
			else if (expression.charAt(i) == ')') {
				if (operator || bracketL) {
					return null;
				}
				bracketRCounter ++;
				while ((!operators.isEmpty())&&((char)operators.peek() != '(')) {
					if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ') {
						postFix += ' ' ;
					}
					postFix += operators.pop();
				}
				if ((!operators.isEmpty())&&((char)operators.peek() == '(')) {
					operators.pop();
				}
				operand = false;
				operator = false;
				bracketL = false;
				bracketR = true;
				space = false;
				unary = false;
			}
			/**
			 * expression is invalid for any other character
			 */
			else if (expression.charAt(i) != ' ') {
				return null;
			}
		}
		/**
		 * expression is invalid if it ends with operator or left bracket
		 */
		if (operator || bracketL) {
			return null;
		}
		/**
		 * expression is invalid if number of left brackets doesn't equal number of right brackets
		 */
		if (bracketLCounter != bracketRCounter) {
			return null;
		}
		/**
		 * remaining operations
		 */
		while (!operators.isEmpty()) {
			if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) != ' ') {
				postFix += ' ' ;
			}
			postFix += operators.pop();
		}
		if ((postFix.length() != 0)&&postFix.charAt(postFix.length()-1) == ' ') {
			postFix = postFix.substring(0, postFix.length() -1);
		}
		return postFix;
	}

	@Override
	public int evaluate(String expression) {
		int i , j  , counter = 0 ;
		Stack operation = new Stack();
		float temp = 0;
		boolean unary = false;
		for (i = 0 ; i < expression.length() ; i++) {
			if (Character.isDigit(expression.charAt(i))) {
				for (j = i+1 ; j < expression.length() ; j++) {
					if (Character.isDigit(expression.charAt(j))) {
						counter++;
					}
					else {
						break;
					}
				}
				/**
				 * special treatment for unary operations in evaluation
				 */
				if (unary) {
					operation.push(-Float.valueOf(expression.substring(i, i + counter +1)));
				}
				else {
					operation.push(Float.valueOf(expression.substring(i, i + counter +1)));
				}
				unary = false;
				i = j-1 ;
				counter = 0 ;
			}
			else if (expression.charAt(i) == '+') {
				temp = (float)operation.pop();
				/**
				 * if it is first term as unary 
				 */
				if (operation.isEmpty()) {
					operation.push(temp);
				}
				else {
					operation.push(temp + (float) operation.pop());
				}
			}
			else if (expression.charAt(i) == '-' && i+1 < expression.length() && Character.isDigit(expression.charAt(i+1))) {
				unary = true;
			}
			else if (expression.charAt(i) == '-') {
				temp = - (float)operation.pop();
				if (operation.isEmpty()) {
					operation.push(temp);
				}
				else {
					operation.push(temp + (float)operation.pop());
				}
			}
			else if (expression.charAt(i) == '*') {
				operation.push((float)operation.pop() * (float)operation.pop());
			}
			else if (expression.charAt(i) == '/') {
				float second = (float)operation.pop();
				float first = (float)operation.pop();
				operation.push((float)(first / second));
			}
		}
		/**
		 * although all operands are integer but they are saved in the stack as float as intermediate results can be float but as the function should return integer value so float result is rounded to an integer
		 */
		return Math.round((float)operation.pop());
	}
}
