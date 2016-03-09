package tab.price.pojo;

import java.util.Map;

/**
 * Order.
 * 
 * @author Tab
 *
 */
public class Order {
	/**
	 * Items of the order.
	 */
	private Map<String, Item> items;
	/**
	 * Order total.
	 */
	private double amount;
	/**
	 * Item level discount (May have order level discunt later).
	 */
	private double itemDiscount;

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

}
