/**
 * @author Ishika Gupta
 * @class CSS 143 A
 * @assignment Grocery Manager, Produce
 * @date 12/12/20 
 */

/**
 * 
 * @version 1.0 Produce extends GroceryItem and creates and stores all
 *          properties of Produce.
 *
 */
public class Produce extends GroceryItem {
	// instance variable boolean organic representing whether or not the produce is
	// organic
	private boolean organic;

	/**
	 * This Produce class constructor calls the arg base class constructor that
	 * takes in the arguments name, quantity, and price from the Produce constructor
	 * parameter. It then initializes instance variable organic to the parameter
	 * boolean isOrganic.
	 * 
	 * @param name
	 * @param quantity
	 * @param price
	 * @param isOrganic
	 */
	public Produce(String name, int quantity, double price, boolean isOrganic) {
		super(name, quantity, price);
		this.organic = isOrganic;
	}

	/**
	 * This class constructor takes in file input line, splits the inputLine based
	 * on the blank spaces and stores each item delimited by the separator is stored
	 * in order in the produceInput array. It first checks to see if the 0th index
	 * of the array is equal to Produce. If it is, then setName is called setting
	 * name to the 1st index of produceInput. setQuantity is called setting quantity
	 * to the parseInt Integer value of the 2nd index of produceInput. setPrice is
	 * called setting price to the parseDouble Double value of the 3rd index of
	 * produceInput. Finally, instance variable organic is set to parseBoolean
	 * Boolean value of the 4th index of produceInput.
	 * 
	 * @param inputLine
	 */
	public Produce(String inputLine) {
		String[] produceInput = inputLine.split(" ");
		if (produceInput[0].equals("Produce")) {
			this.setName(produceInput[1]);
			this.setQuantity(Integer.parseInt(produceInput[2]));
			this.setPrice(Double.parseDouble(produceInput[3]));
			this.organic = Boolean.parseBoolean(produceInput[4]);
		}
	}

	/**
	 * This method sets instance variable organic to the parameter boolean organic
	 * value.
	 * 
	 * @param organic
	 */
	public void isOrganic(boolean organic) {
		this.organic = organic;
	}

	/**
	 * This Accessor method accesses and returns the instance variable organic
	 * 
	 * @return organic
	 */
	public boolean getIsOrganic() {
		return this.organic;
	}

	@Override
	/**
	 * This overridden toString calls the toString of the base class. It then
	 * concatenates it with formatted String that prints isOrganic using argument
	 * given by Accessor method getIsOrganic.
	 */
	public String toString() {
		return super.toString() + String.format("isOrganic: %-20b", getIsOrganic());
	}
}
