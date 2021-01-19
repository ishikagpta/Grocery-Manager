
/**
 * @author Ishika Gupta
 * @class CSS 143 A
 * @assignment Grocery Manager, GroceryItem 
 * @date 12/12/20 
 */

import java.text.DecimalFormat;

/**
 * 
 * @version 1.0 GroceryItem is an abstract class that implements Comparable and
 *          is a base class that represents Grocery Items that is used by its
 *          subclasses Dairy, Produce, and Meat.
 *
 */
public abstract class GroceryItem implements Comparable {
	// instance variables made to represent name, quantity, and price of a grocery
	// item
	private String name;
	private int quantity;
	private double price;

	/**
	 * Class constructor that initializes instance variables name, quantity, and
	 * price to parameter variables name, quantity, and price.
	 * 
	 * @param name
	 * @param quantity
	 * @param price
	 */
	public GroceryItem(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * No arg Class constructor that initializes every instance variable to each of
	 * it's type's version of 0
	 */
	public GroceryItem() {
		this.name = "";
		this.quantity = 0;
		this.price = 0.00;
	}

	/**
	 * Accessor method that returns the name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Mutator that sets the instance variable name to the parameter name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accessor that returns the quantity
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Mutator that sets the instance variable quantity to the parameter quantity
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Accessor that returns the price
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Mutator that sets the instance variable price to the parameter price
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	/**
	 * This overridden toString creates an object formatted of DecimalFormat that
	 * has a string pattern given in its parameter. It then returns a formatted
	 * String that takes in arguments Accessor methods getName, getQuantity, and a
	 * formatted based on object formatted getPrice
	 */
	public String toString() {
		DecimalFormat formatted = new DecimalFormat("#.##");
		return String.format("Name: %-20s Quantity: %-20d Price: $%-20s", getName(), getQuantity(),
				formatted.format(getPrice()));
	}

	/**
	 * This compareTo method implements the Comparable interface where it first
	 * checks to see if the other Object is not null and if the other Object class
	 * is the same as this Object's class. If the conditions are met, GroceryItem
	 * compare is set to the other Object typecasted to GroceryItem. It then returns
	 * this name GroceryItem's name compareTo compare GroceryItem's name. If the
	 * original if condition was not met, the method drops down and returns -0.
	 */
	public int compareTo(Object other) {
		if (other != null && other.getClass() == this.getClass()) {
			GroceryItem compare = (GroceryItem) other;
			return (this.name.compareTo(compare.name));
		}
		return -0;
	}

}
