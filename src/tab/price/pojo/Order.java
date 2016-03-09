package tab.price.pojo;

import java.util.Map;

public class Order {
	private Map<String, Item> items;
	private double amount;

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

}
