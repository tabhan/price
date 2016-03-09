package tab.price.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import tab.price.data.DataManager;
import tab.price.data.SimpleDataManager;
import tab.price.pojo.Product;
import tab.price.promotion.Promotion;

public class TestDataManager {

	private DataManager manager = new SimpleDataManager();

	@Test
	public void testQueryProduct() {

		String productId = "ITEM000001";
		double perUnitPrice = 3d;
		String name = "\u53ef\u53e3\u53ef\u4e50";

		Product product = manager.getProduct(productId);

		Assert.assertNotNull(product);
		Assert.assertEquals(productId, product.getProductId());
		Assert.assertEquals(perUnitPrice, product.getPerUnitPrice(), 0);
		Assert.assertEquals(name, product.getName());

	}

	@Test
	public void testQueryPromotions() {

		String[] ids = { "p1", "p2" };
		int[] priorities = { 2, 1 };

		List<Promotion> promotions = manager.getAllPromotions();

		Assert.assertNotNull(promotions);
		Assert.assertEquals(2, promotions.size());

		Assert.assertEquals(ids[0], promotions.get(0).getId());
		Assert.assertEquals(ids[1], promotions.get(1).getId());
		Assert.assertEquals(priorities[0], promotions.get(0).getPriority());
		Assert.assertEquals(priorities[1], promotions.get(1).getPriority());
	}
}
