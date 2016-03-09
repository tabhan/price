package tab.price.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import tab.price.PricingEngine;
import tab.price.data.DataManager;
import tab.price.data.SimpleDataManager;
import tab.price.parse.InputParser;
import tab.price.parse.JSONInputParser;
import tab.price.pojo.Item;
import tab.price.pojo.Order;
import tab.price.promotion.Promotion;

public class TestPriceEngine {

	private static PricingEngine pricingEngine;
	private static InputParser inputParser;

	@BeforeClass
	public static void init() {
		inputParser = new JSONInputParser();
		pricingEngine = new PricingEngine();
		pricingEngine.setDataManager(new SimpleDataManager());
	}

	@Test
	public void testQueryItemPrice() {
		String productId = "ITEM000001";
		double perUnitPrice = 3d;
		String name = "\u53ef\u53e3\u53ef\u4e50";

		Item item = new Item();
		item.setProductId(productId);

		pricingEngine.queryProductInfo(item);

		Assert.assertEquals(productId, item.getProductId());
		Assert.assertEquals(perUnitPrice, item.getPerUnitPrice(), 0);
		Assert.assertEquals(name, item.getName());
	}

	@Test
	public void testSortPromotion() {
		String[] ids = { "p2", "p1" };
		int[] priorities = { 1, 2 };
		DataManager manager = new SimpleDataManager();
		List<Promotion> promotions = manager.getAllPromotions();
		pricingEngine.sortPromotions(promotions);

		Assert.assertNotNull(promotions);
		Assert.assertEquals(2, promotions.size());

		Assert.assertEquals(ids[0], promotions.get(0).getId());
		Assert.assertEquals(ids[1], promotions.get(1).getId());
		Assert.assertEquals(priorities[0], promotions.get(0).getPriority());
		Assert.assertEquals(priorities[1], promotions.get(1).getPriority());
	}

	@Test
	public void testPrice() {
		String key1 = "ITEM000001";
		String key3 = "ITEM000003";
		String key5 = "ITEM000005";
		String input = "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";
		InputParser parser = new JSONInputParser();
		Order order = parser.parse(input);

		pricingEngine.price(order);

		Assert.assertEquals(12, order.getItems().get(key1).getAmount(), 0);
	}
}
