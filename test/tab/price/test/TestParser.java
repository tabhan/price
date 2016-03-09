package tab.price.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import tab.price.parse.InputParser;
import tab.price.parse.JSONInputParser;
import tab.price.pojo.Item;
import tab.price.pojo.Order;

public class TestParser {

	@Test
	public void testJSONInputParser() {

		String key1 = "ITEM000001";
		String key3 = "ITEM000003";
		String key5 = "ITEM000005";
		Set<String> keys = new HashSet<>();
		keys.add(key1);
		keys.add(key3);
		keys.add(key5);
		String input = "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";

		InputParser parser = new JSONInputParser();

		Order order = parser.parse(input);

		Assert.assertNotNull(order);
		Assert.assertNotNull(order.getItems());
		Assert.assertEquals(keys, order.getItems().keySet());
		Assert.assertEquals(5, order.getItems().get(key1).getQuantity());
		Assert.assertEquals(2, order.getItems().get(key3).getQuantity());
		Assert.assertEquals(3, order.getItems().get(key5).getQuantity());
	}

	/**
	 * parser.parseItem "ITEM000001"
	 */
	@Test
	public void testParseProductId() {
		String input = "ITEM000001";
		long qty = 1;
		JSONInputParser parser = new JSONInputParser();

		Item item = parser.parseItem(input);

		Assert.assertNotNull(item);
		Assert.assertEquals(input, item.getProductId());
		Assert.assertEquals(qty, item.getQuantity());
	}

	/**
	 * parser.parseItem "ITEM000003-2"
	 */
	@Test
	public void testParseProductIdQty() {
		String id = "ITEM000003";
		long qty = 2;
		String input = id + "-" + qty;
		JSONInputParser parser = new JSONInputParser();

		Item item = parser.parseItem(input);

		Assert.assertNotNull(item);
		Assert.assertEquals(id, item.getProductId());
		Assert.assertEquals(qty, item.getQuantity());
	}

	/**
	 * parser.parseItem "ITEM000001-d3"
	 */
	@Test
	public void testParseProductIdQtyErrFmt() {
		String input = "ITEM000001-d3";
		long qty = 1;
		JSONInputParser parser = new JSONInputParser();

		Item item = parser.parseItem(input);

		Assert.assertNotNull(item);
		Assert.assertEquals(input, item.getProductId());
		Assert.assertEquals(qty, item.getQuantity());
	}

	/**
	 * parser.addItemToOrder
	 */
	@Test
	public void testAddItemToOrder() {

		String id = "ITEM000001";

		Order order = new Order();
		Item item = new Item();
		item.setProductId(id);
		item.setQuantity(1);
		Assert.assertNull(order.getItems());

		// add 1st
		JSONInputParser parser = new JSONInputParser();
		parser.addItemToOrder(order, item);

		Assert.assertNotNull(order.getItems());
		Assert.assertEquals(id, order.getItems().get(id).getProductId());
		Assert.assertEquals(1, order.getItems().get(id).getQuantity());

		// add 2nd
		item = new Item();
		item.setProductId(id);
		item.setQuantity(1);
		parser.addItemToOrder(order, item);
		Assert.assertNotNull(order.getItems());
		Assert.assertEquals(id, order.getItems().get(id).getProductId());
		Assert.assertEquals(2, order.getItems().get(id).getQuantity());

		// add 3 more
		item = new Item();
		item.setProductId(id);
		item.setQuantity(3);
		parser.addItemToOrder(order, item);
		Assert.assertNotNull(order.getItems());
		Assert.assertEquals(id, order.getItems().get(id).getProductId());
		Assert.assertEquals(5, order.getItems().get(id).getQuantity());

		// add another
		item = new Item();
		String otherId = "ITEM000002";
		item.setProductId(otherId);
		item.setQuantity(2);
		parser.addItemToOrder(order, item);
		Assert.assertNotNull(order.getItems());
		Assert.assertEquals(id, order.getItems().get(id).getProductId());
		Assert.assertEquals(5, order.getItems().get(id).getQuantity());
		Assert.assertEquals(otherId, order.getItems().get(otherId).getProductId());
		Assert.assertEquals(2, order.getItems().get(otherId).getQuantity());
		Assert.assertEquals(2, order.getItems().size());
	}
}
