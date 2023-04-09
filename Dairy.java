/**
 * @author Ishika Gupta
 * @class CSS 143 A
 * @assignment Grocery Manager, Dairy 
 * @date 12/12/20 
 */

/**
 * 
 * @version 1.0 Dairy extends GroceryItem and creates and stores all properties
 *          of Dairy. 
 *
 */
public class Dairy extends GroceryItem {
	// instance variable that stores the refrigeration temperature of the dairy item
	private int refrigerationTemperature;

	/**
	 * This Dairy class constructor calls the arg base class constructor that takes
	 * in the arguments name, quantity, and price from the Dairy constructor
	 * parameter. It then initializes instance variable refrigerationTemperature to
	 * the parameter int refrigerationTemperature.
	 * 
	 * @param name
	 * @param quantity
	 * @param price
	 * @param refrigerationTemperature
	 */
	public Dairy(String name, int quantity, double price, int refrigerationTemperature) {
		super(name, quantity, price);
		this.refrigerationTemperature = refrigerationTemperature;
	}

	/**
	 * This class constructor takes in file input line, splits the inputLine based
	 * on the blank spaces and stores each item delimited by the separator is stored
	 * in order in the dairyInput array. It first checks to see if the 0th index of
	 * the array is equal to Dairy. If it is, then setName is called setting name to
	 * the 1st index of dairyInput. setQuantity is called setting quantity to the
	 * parseInt Integer value of the 2nd index of dairyInput. setPrice is called
	 * setting price to the parseDouble Double value of the 3rd index of dairyInput.
	 * Finally, instance variable refrigerationTemperature is set to parseInt
	 * Integer value of the 4th index of dairyInput.
	 * 
	 * @param inputLine
	 */
	public Dairy(String inputLine) {
		String[] dairyInput = inputLine.split(" ");
		if (dairyInput[0].equals("Dairy")) {
			this.setName(dairyInput[1]);
			this.setQuantity(Integer.parseInt(dairyInput[2]));
			this.setPrice(Double.parseDouble(dairyInput[3]));
			this.refrigerationTemperature = Integer.parseInt(dairyInput[4]);
		}
	}

	/**
	 * This Mutator method sets instance variable refrigerationTemperature to the
	 * parameter int temp value.
	 * 
	 * @param temp
	 */
	public void setRefrigerationTemperature(int temp) {
		this.refrigerationTemperature = temp;
	}

	/**
	 * This accessor method accesses and returns instance variable
	 * refrigerationTemperature
	 * 
	 * @return refrigerationTemperature
	 */
	public int getRefrigerationTemperature() {
		return this.refrigerationTemperature;
	}

	@Override
	/**
	 * This overridden toString calls the toString of the base class. It then
	 * concatenates it with formatted String that prints Temperature using argument
	 * given by Accessor method getRefrigerationTemperature.
	 */
	public String toString() {
		return super.toString() + String.format("Temperature: %-20d", getRefrigerationTemperature());
	}
}
