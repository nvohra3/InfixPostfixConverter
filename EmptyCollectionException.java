/**
 * Subclass of RuntimeException
 * Thrown when user attempts to peek or pop empty array stack
 * @author Neil Vohra
 */
public class EmptyCollectionException extends RuntimeException {
	
	/**
	 * Constructs exception with an appropriate message. 
	 * @param collection
	 * the name of the collection
	 */
	public EmptyCollectionException(String collection) {
		super("The " + collection + " is empty.");
	}
}