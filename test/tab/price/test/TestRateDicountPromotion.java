package tab.price.test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import tab.price.pojo.Item;
import tab.price.pojo.Order;
import tab.price.promotion.RateDiscountPromotion;

public class TestRateDicountPromotion {

	private RateDiscountPromotion promotion = new RateDiscountPromotion();

	@Test
	public void testApplyPromotion() {

		String productId = "ITEM000001";

		promotion.setProductId(productId);
		promotion.setDiscountRate(0.5);

		Order order = new Order();
		order.setItems(new HashMap<>());
		Item item = new Item();

		item.setProductId("ITEM000001");
		item.setPerUnitPrice(3);
		order.getItems().put(item.getProductId(), item);

		promotion.applyPromotion(order);

		Assert.assertNotNull(item.getDiscountInfo());
		Assert.assertEquals(1.5, item.getDiscountInfo().getPerUnitDiscount(), 0);
	}
}
