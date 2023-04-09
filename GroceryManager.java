
/**
 * @author Ishika Gupta
 * @class CSS 143 A
 * @assignment Grocery Manager, GroceryManager
 * @date 12/12/20
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 * @version 1.0 GroceryManager is the class that pulls everything together and
 *          handles the grocery inventory, orders, and reorders. It also sorts
 *          the orders and prints them and the reorders. It handles essentially
 *          any larger grocery function that is utilized in the GroceryDriver.
 *
 */
public class GroceryManager {
	private ArrayList<GroceryItem> inventory = new ArrayList<>();
	private HashSet<String> reorderList = new HashSet<>();

	/**
	 * loadInventory method takes file name as parameter and reads the file. In the
	 * first line of the inventory file, it captures the number of items of dairy,
	 * produce, and meat and stores them as NumDairy, NumProduce, and NumMeat. Then,
	 * for each line next in the file, it increments the itemCount. It then adds the
	 * line of the dairy, produce, or meat to the inventory so long as the itemCount
	 * is less than or equal to total number of items so far represented as NumDairy
	 * for Dairy and NumProduce plus NumDairy for Produce. Rest of the items are
	 * Meat. This is all done in a try statement therefore if anything goes wrong,
	 * it drops down to the catch exception where the exception is caught. Finally,
	 * the input is closed.
	 * 
	 * @param filename
	 */
	public void loadInventory(String filename) {
		Scanner input = null;
		String line;
		try {
			input = new Scanner(new FileInputStream(filename));
			line = input.nextLine();
			String[] StrInv = line.split(" ");

			int NumDairy = Integer.parseInt(StrInv[0]);
			int NumProduce = Integer.parseInt(StrInv[1]);
			int NumMeat = Integer.parseInt(StrInv[2]);
			int ItemCount = 0;

			while (input.hasNext()) {
				String line1 = input.nextLine();
				ItemCount++;
				if (ItemCount <= NumDairy) {
					inventory.add(new Dairy(line1));
				} else if (ItemCount <= NumProduce + NumDairy) {
					inventory.add(new Produce(line1));
				} else {
					inventory.add(new Meat(line1));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			input.close();
		}
	}

	/**
	 * The processOrder method reads the order from ArrayList GroceryOrder and for
	 * each item in the order it stores the itemName and quantity as a GroceryItem
	 * object in the ItemName and Qty variables. Then, it creates a GroceryItem
	 * variable called Groc_Itm and assigns it to a method called findItemByName
	 * that takes in ItemName and returns the inventory that is associated with that
	 * item. If then the Groc_Itm is not null, the Groc_Itm inventory quantity minus
	 * the Qty of the specific item is set to Qty. If then Qty is greater than 0, It
	 * then calls the helper method amtChange that changes the quantity of that
	 * specific item in the inventory. Else if this condition was not met and
	 * Groc_Itm is still not equal to null and the Qty is equal to 0, The amtChange
	 * method is then called that sets the quantity of the specific item in the
	 * inventory to 0. It then adds the item to the reorderList. Else, The amtChange
	 * method is then called that sets the quantity of the specific item in the
	 * inventory to 0. It then adds the item to the reorderList. It then checks to
	 * see if the item is an instance of Dairy. If it is, it removes from the 0th
	 * index of the GroceryOrder ArrayList and calls processOrder. Else if the item
	 * is an instance of Produce. If it is, it removes from the 0th index of the
	 * GroceryOrder ArrayList twice and calls processOrder. It then also throws a
	 * GroceryException showing that the order demanded more than the inventory had
	 * printing an Out of the item statement. Now if Groc_Itm was null from the
	 * start, it drops down to the else statement where an exception is thrown that
	 * the item is not found.
	 * 
	 * @param order
	 * @throws GroceryException
	 */
	public void processOrder(GroceryOrder<GroceryItem> order) throws GroceryException {
		int Qty = 0;
		String ItemName = "";

		for (Object item : order) {
			Qty = ((GroceryItem) item).getQuantity();
			ItemName = ((GroceryItem) item).getName();

			GroceryItem Groc_Itm = findItemByName(ItemName);
			if (Groc_Itm != null) {
				// calculate QTY
				Qty = Groc_Itm.getQuantity() - Qty;
				if (Qty > 0) {
					amtChange(Groc_Itm.getName(), Qty);
				} else if (Qty == 0) {
					amtChange(Groc_Itm.getName(), 0);
					reorderList.add(Groc_Itm.getName());
				} else {
					amtChange(Groc_Itm.getName(), 0);
					reorderList.add(Groc_Itm.getName());
					if (item instanceof Dairy) {
						order.remove(0);
						processOrder(order);
					} else if (item instanceof Produce) {
						order.remove(0);
						order.remove(0);
						processOrder(order);
					}
					throw new GroceryException("Out of: " + Groc_Itm.getName());
				}
			} else {
				throw new GroceryException(ItemName + ": not found in inventory.");
			}
		}
	}

	/**
	 * This method takes in a String name of the item. It then creates a for each
	 * loop where it goes through every item in the inventory. If the item name is
	 * equal to the parameter item name, it goes into a try statement where item of
	 * type GroceryItem is set to a GroceryItem cast type of the item and then item
	 * is returned. It also catches any exceptions thrown and prints them. If item
	 * is never found, down below it returns null.   
	 * 
	 * @param Item_Name
	 * @return item, null
	 */
	public GroceryItem findItemByName(String Item_Name) {
		for (GroceryItem Itm : inventory) {
			if (Itm.getName().equals(Item_Name)) {
				try {
					GroceryItem item = (GroceryItem) Itm;
					return item;
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return null;
	}

	/**
	 * This helper method takes in the name of the item and the quantity of the item
	 * as parameters. Then, for each item in the inventory, if the item name is
	 * equal to the item name in the parameter, the item quantity in the inventory
	 * is set to the quantity given by the parameter.
	 * 
	 * @param Item_Name
	 * @param Item_QTY
	 */
	private void amtChange(String Item_Name, int Item_QTY) {
		for (GroceryItem Item : inventory) {
			if (Item.getName().equals(Item_Name)) {
				Item.setQuantity(Item_QTY);
			}
		}
	}

	/**
	 * This method does a bubble sort that uses compareTo to sort the inventory by
	 * name. It first does a for loop that goes until the size of the inventory. In
	 * the for loop, minimum is set to the helper method obtainLeast that takes in
	 * the inventory, the current position, and the size of the inventory that
	 * returns the index of the lexicographical smallest item name in the inventory.
	 * Then, it calls the helper flipValues method that takes in the inventory,
	 * current position, and the minimum index that was determined in the step
	 * above. This helper method flips the item name at current position j with
	 * minimum index and vice versa.
	 */
	public void sortInventoryByName() {
		int min = 0;
		int size = inventory.size();

		// bubble sort
		for (int j = 0; j < size; j++) {
			min = obtainLeast(inventory, j, size);
			flipValues(inventory, j, min);
		}
	}

	/**
	 * This method does a selection sort to sort the inventory by price. It first
	 * does a for loop that goes until less than the size of the inventory minus 1.
	 * In the for loop, it creates a nested for loop that goes until the size of the
	 * inventory minus the current position of the outer for loop minus 1. Within
	 * this for loop, it checks to see if the current inventory item price is
	 * greater than the current position of the inner for loop plus 1 item price. If
	 * it is then, it flips their positions by calling the helper flip method.
	 */
	public void sortInventoryByPrice() {
		int size = inventory.size();
		// O(n^2) for Selection sort
		for (int x = 0; x < size - 1; x++)
			for (int y = 0; y < size - x - 1; y++)
				if (inventory.get(y).getPrice() > inventory.get(y + 1).getPrice()) {
					flip(inventory, y);
				}
	}

	/**
	 * This helper flip method takes in an ArrayList of type GroceryItem called arl
	 * and takes in an int i in the parameter. It uses a copied variable of type
	 * GroceryItem that is set to the ArrayList item at the parameter i index. It
	 * then sets the ArrayList at the parameter index to the value at the parameter
	 * index plus one. It also then sets the ArrayList at the parameter index plus
	 * one to the value copied.
	 * 
	 * @param arl
	 * @param i
	 */
	private void flip(ArrayList<GroceryItem> arl, int i) {
		GroceryItem copied = arl.get(i);
		arl.set(i, arl.get(i + 1));
		arl.set(i + 1, copied);
	}

	/**
	 * This helper method takes in an ArrayList of type GroceryItem called arl, an
	 * int j, and an int nextSmallest. It uses a copied variable of type GroceryItem
	 * that is set to the ArrayList item at the parameter j index. It then sets the
	 * ArrayList at the parameter index j to the value at the parameter index
	 * nextSmallest. It also then sets the ArrayList at the parameter index
	 * nextSmallest to the value copied.
	 * 
	 * @param arl
	 * @param j
	 * @param nextSmallest
	 */
	private void flipValues(ArrayList<GroceryItem> arl, int j, int nextSmallest) {
		GroceryItem copied = arl.get(j);
		arl.set(j, arl.get(nextSmallest));
		arl.set(nextSmallest, copied);
	}

	/**
	 * This helper method takes in an ArrayList of type GroceryItem called
	 * Groc_Item, an int Start, and an int Finish. It first creates a variable
	 * called minimum that is set to the parameter Start value. It also creates an
	 * int check that is initialized to 0. It then creates a for loop that starts at
	 * the parameter Start and goes until less than the Finish parameter value. In
	 * this for loop, if check is less than the compareTo comparison of the
	 * ArrayList item name at minimum index and ArrayList item name at the current
	 * position j of the for loop, minimum index is then set to the current position
	 * of the for loop j. After this for loop, the minimum index is returned.
	 * 
	 * @param Groc_Item
	 * @param Start
	 * @param Finish
	 * @return minimum
	 */
	private int obtainLeast(ArrayList<GroceryItem> Groc_Item, int Start, int Finish) {
		int minimum = Start;
		int check = 0;
		for (int j = Start; j < Finish; j++) {
			if (check < Groc_Item.get(minimum).getName().compareTo(Groc_Item.get(j).getName())) {
				minimum = j;
			}
		}
		return minimum;
	}

	/**
	 * This method displays the inventory by doing a for loop through item in the
	 * inventory and printing it out.
	 */
	public void displayInventory() {
		for (GroceryItem itm : inventory)
			System.out.println(itm);
	}

	/**
	 * This method displays the reorders needed. It first creates a local String
	 * variable reorders. It then goes through each reorder in the reorder list and
	 * printing them while setting reorders to reorders plus the reorder plus next
	 * line character. It finally then returns the reorders variable.
	 * 
	 * @return reorders
	 */
	public String displayReorders() {
		String reorders = "";
		for (String reorder : reorderList) {
			System.out.println(reorder);
			reorders = reorders + reorder + "\n";
		}
		return reorders;
	}
}
