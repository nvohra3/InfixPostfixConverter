import java.util.Scanner;

/**
 * Class to allow user to test Converter class
 * @author Neil Vohra
 */
public class Project3Runner {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean menuLoop = true;
		Converter convert = new Converter();
		String expression = "";
		System.out.println("Please enter input using format a * ( b + c )");
				
		while (menuLoop)
		{
			System.out.println("Enter your choice:");
			System.out.println("1 --- Convert infix to postfix");
			System.out.println("2 --- Convert postfix to infix");
			System.out.println("3 --- Exit");
			String choice = in.nextLine();
						
			switch (Integer.parseInt(choice)) {
			case 1: System.out.println("Enter an infix expression: ");
					expression = in.nextLine();
					System.out.println("In Postfix Notation: " + convert.infixToPostfix(expression));
					break;
			case 2: System.out.println("Enter a postfix expression: ");
					expression = in.nextLine();
					System.out.println("In Infix Notation: " + convert.postfixToInfix(expression));
					break;
			case 3: break;
			}
			System.out.println("Go again? (Type yes or no)");
			if (in.nextLine().equalsIgnoreCase("yes"))
				menuLoop = true;
			else
				menuLoop = false;
		}
	}

}
