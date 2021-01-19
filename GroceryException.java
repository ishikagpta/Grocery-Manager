/**
 * @author Ishika Gupta
 * @class CSS 143 A
 * @assignment Grocery Manager, GroceryException
 * @date 12/12/20
 */

/**
 * 
 * @version 1.0 GroceryException inherits the Exception class to be used as an
 *          exception class for the GroceryManager class and overall project
 *
 */
public class GroceryException extends Exception {
	/**
	 * This is a no arg class constructor that calls the parent constructor in its
	 * body that contains a message
	 */
	public GroceryException() {
		super("This grocery data is invalid!");
	}

	/**
	 * This is a class constructor that takes in String message as an argument and
	 * calls the parent constructor in its body to use and print the argument
	 * 
	 * @param message
	 */
	public GroceryException(String message) {
		super(message);
	}
}
