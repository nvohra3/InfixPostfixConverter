/**
 * Subclass of RuntimeException
 * Thrown when user inputs invalid expression to convert
 * @author Neil Vohra
 */
public class InvalidInputException extends RuntimeException {
	public InvalidInputException() {
		super ("Invalid input.");
	}
}
