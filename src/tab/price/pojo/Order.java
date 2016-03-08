package tab.price.pojo;

import java.util.Map;

public class Order {
	private Map<String, Item> items;

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

}
