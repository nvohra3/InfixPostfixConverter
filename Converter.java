import java.util.ArrayList;

/**
 * Allows user to create Converter objects to convert infix 
 * expressions to postfix expressions and vice versa
 * @author Neil Vohra
 */
public class Converter {
	private final static String ADD = "+";
	private final static String SUBTRACT = "-";
	private final static String MULTIPLY = "*";
	private final static String DIVIDE = "/";
	private final static String LEFT_PARENTHESIS = "(";
	private final static String RIGHT_PARENTHESIS = ")";

	/**
	 * Converts infix expression to postfix expression
	 * @param expression line to convert
	 * @return postfix equivalent of expression
	 * @throws InvalidInputException if input is invalid
	 */
	public String infixToPostfix(String expression) throws InvalidInputException {
		ArrayStack<String> stack = new ArrayStack<String>();
		ArrayList<String> output = new ArrayList<String>();
		int count = 0;
		String[] stringArray = expression.split(" ");
		if (!(inputCheck(stringArray) == true))
			throw new InvalidInputException();
		while (count < stringArray.length) {
			String token = stringArray[count];
			if (token.equals(LEFT_PARENTHESIS)) {
				stack.push(token);
			} else if (token.equals(RIGHT_PARENTHESIS)) { // Pop operators until left parenthesis is left on stack
				while (!stack.peek().equals(LEFT_PARENTHESIS)) {
					output.add(stack.pop());
					if (stack.size() == 1 && !stack.peek().equals(LEFT_PARENTHESIS))
						throw new InvalidInputException();
				}
				stack.pop();
			} else if (isOperator(token)) {
				String o1 = token;
				while (!stack.isEmpty() && isOperator(stack.peek())) {
					String o2 = stack.peek();
					if (getPrecedence(o1) <= getPrecedence(o2)) {
						output.add(stack.pop());
					} else {
						break;
					}
				}
				stack.push(o1);
			} else {
				output.add(token); // Reaches if token is an operand
			}
			count++;
		}

		while (!stack.isEmpty())
			output.add(stack.pop());

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < output.size(); i++)
			sb.append(output.get(i) + " ");
		return sb.toString();
	}

	/**
	 * Converts postfix expression to infix expression
	 * @param expressionline to convert
	 * @return infix equivalent of expression
	 * @throws InvalidInputException if input is invalid
	 */
	public String postfixToInfix(String expression) throws InvalidInputException {
		ArrayStack<String> stack = new ArrayStack<String>();
		int count = 0;
		String[] stringArray = expression.split(" ");
		if (!(inputCheck(stringArray) == true))
			throw new InvalidInputException();
		while (count < stringArray.length) {
			String token = stringArray[count];
			if (!isOperator(token)) {
				stack.push(token);
			}
			if (isOperator(token)) {
				if (stack.size() < 2)
					throw new InvalidInputException();
				String lastOperator = stack.pop();
				String firstOperator = stack.pop();
				stack.push(operationFormer(token, firstOperator, lastOperator));
			}
			count++;
		}
		return stack.pop();
	}

	/**
	 * Join two operands and one operator to form a string
	 * @param op operator
	 * @param operand1 first operand
	 * @param operand2 second operand
	 * @return single string of operator and operands
	 */
	public String operationFormer(String op, String operand1, String operand2) {
		return LEFT_PARENTHESIS + operand1 + " " + op + " " + operand2 + RIGHT_PARENTHESIS;
	}

	public boolean inputCheck(String[] array) {
		boolean valid = true;
		for (int i = 0; i < array.length; i++) {
			String token = array[i];
			try {
				Double.parseDouble(token);
			} catch (NumberFormatException e) {
				valid = false;
			}

			if (valid == false) {
				if (token.equals(ADD) || token.equals(SUBTRACT)
						|| token.equals(MULTIPLY) || token.equals(DIVIDE)
						|| token.equals(LEFT_PARENTHESIS)
						|| token.equals(RIGHT_PARENTHESIS))
					valid = true;
				else
					return false;
			}
		}
		return valid;
	}

	/**
	 * Returns precedence of operator
	 * @param op1 operator
	 * @return precedence of operator
	 */
	public int getPrecedence(String operator) {
		if (operator.equals(ADD) || operator.equals(SUBTRACT))
			return 2;
		if (operator.equals(MULTIPLY) || operator.equals(DIVIDE))
			return 3;
		return 0;
	}

	/**
	 * @param obj string to test
	 * @return true if parameter is an operator
	 */
	public boolean isOperator(String param) {
		return param.equals(ADD) || param.equals(SUBTRACT)
				|| param.equals(MULTIPLY) || param.equals(DIVIDE);
	}
}