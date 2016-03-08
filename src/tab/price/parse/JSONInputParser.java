package tab.price.parse;

import java.util.LinkedHashMap;

import com.google.gson.Gson;

import tab.price.pojo.Item;
import tab.price.pojo.Order;

/**
 * parser that the input string must with JSON format.
 * 
 * @author Administrator
 *
 */
public class JSONInputParser implements InputParser {

	/**
	 * gson.
	 */
	private Gson gson = new Gson();

	private String quntitySeperator = "-";

	@Override
	public Order parse(String input) {
		Order result = null;
		String[] inputInfo = gson.fromJson(input, String[].class);
		if (inputInfo != null) {
			result = new Order();
			Item item = null;
			for (String inputItem : inputInfo) {
				item = parseItem(inputItem);
				addItemToOrder(result, item);
			}
		}
		return result;
	}

	/**
	 * Parse item from input string. ITEM000001 -> id=ITEM000001 ITEM000003-2 ->
	 * id=ITEM000003,quantity=2
	 * 
	 * @param inputItem
	 * @return
	 */
	public Item parseItem(String inputItem) {

		if (inputItem == null || inputItem.isEmpty()) {
			return null;
		}

		Item item = new Item();
		int idx = inputItem.indexOf(getQuntitySeperator());

		if (idx >= 0) {

			try {
				item.setId(inputItem.substring(0, idx));
				String strQty = inputItem.substring(idx + getQuntitySeperator().length());
				item.setQuantity(Long.parseLong(strQty));
			} catch (NumberFormatException e) {
				item.setId(inputItem);
				item.setQuantity(1);
			}
		} else {
			item.setId(inputItem);
			item.setQuantity(1);
		}

		return item;

	}

	/**
	 * 
	 * If item already exists in order, add quantity.
	 *
	 * @param order
	 *            order instance
	 * @param item
	 *            item instance
	 */
	public void addItemToOrder(Order order, Item item) {
		
		// null check and initialize.
		if(item == null){
			return;
		}
		if(order.getItems() == null){
			order.setItems(new LinkedHashMap<>());
		}
		
		if (order.getItems().containsKey(item.getId())) {
			Item oriItem = order.getItems().get(item.getId());
			oriItem.setQuantity(oriItem.getQuantity() + item.getQuantity());
		} else {
			order.getItems().put(item.getId(), item);
		}
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public String getQuntitySeperator() {
		return quntitySeperator;
	}

	public void setQuntitySeperator(String quntitySeperator) {
		this.quntitySeperator = quntitySeperator;
	}

}
