/**
 * @author Ishika Gupta
 * @class CSS 143 A
 * @assignment Grocery Manager, Meat 
 * @date 12/12/20 
 */

/**
 * 
 * @version 1.0 This Meat class extends GroceryItem and creates and stores the
 *          properties of Meat
 *
 */
public class Meat extends GroceryItem {
	// instance variable made to represent a boolean of whether the Meat is ground
	// or not
	private boolean ground;

	/**
	 * This Meat class constructor calls the arg base class constructor that takes
	 * in the arguments name, quantity, and price from the Meat constructor
	 * parameter. It then initializes instance variable ground to the parameter
	 * boolean isGround.
	 * 
	 * @param name
	 * @param quantity
	 * @param price
	 * @param isGround
	 */
	public Meat(String name, int quantity, double price, boolean isGround) {
		super(name, quantity, price);
		this.ground = isGround;
	}

	/**
	 * This class constructor takes in file input line, splits the inputLine based
	 * on the blank spaces and stores each item delimited by the separator is stored
	 * in order in the meatInput array. It first checks to see if the 0th index of
	 * the array is equal to Meat. If it is, then setName is called setting name to
	 * the 1st index of meatInput. setQuantity is called setting quantity to the
	 * parseInt Integer value of the 2nd index of meatInput. setPrice is called
	 * setting price to the parseDouble Double value of the 3rd index of meatInput.
	 * Finally, instance variable ground is set to parseBoolean Boolean value of the
	 * 4th index of meatInput.
	 * 
	 * @param inputLine
	 */
	public Meat(String inputLine) {
		String[] meatInput = inputLine.split(" ");
		if (meatInput[0].equals("Meat")) {
			this.setName(meatInput[1]);
			this.setQuantity(Integer.parseInt(meatInput[2]));
			this.setPrice(Double.parseDouble(meatInput[3]));
			this.ground = Boolean.parseBoolean(meatInput[4]);
		}
	}

	/**
	 * This method sets instance variable ground to the parameter boolean ground
	 * value.
	 * 
	 * @param ground
	 */
	public void isGround(boolean ground) {
		this.ground = ground;
	}

	/**
	 * This Accessor method accesses and returns the instance variable ground
	 * 
	 * @return ground
	 */
	public boolean getIsGround() {
		return this.ground;
	}

	@Override
	/**
	 * This overridden toString calls the toString of the base class. It then
	 * concatenates it with formatted String that prints isGround using argument
	 * given by Accessor method getIsGround.  
	 */
	public String toString() {
		return super.toString() + String.format("isGround: %-20b", getIsGround());
	}
}
