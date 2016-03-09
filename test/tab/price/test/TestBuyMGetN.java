package tab.price.test;

import org.junit.Assert;
import org.junit.Test;

import tab.price.promotion.BuyMgetNPromotion;

public class TestBuyMGetN {

	private BuyMgetNPromotion promotion = new BuyMgetNPromotion();

	private long calculateDiscountQuantity(long m, long n, long itemQuantity) {

		promotion.setM(m);
		promotion.setN(n);

		return promotion.calculateDiscountQuantity(itemQuantity);
	}

	@Test
	public void testCalculateDiscountQuantity() {

		Assert.assertEquals(0, calculateDiscountQuantity(3, 1, 1));
		Assert.assertEquals(0, calculateDiscountQuantity(3, 1, 3));
		Assert.assertEquals(1, calculateDiscountQuantity(3, 1, 4));
		Assert.assertEquals(1, calculateDiscountQuantity(3, 1, 7));
		Assert.assertEquals(2, calculateDiscountQuantity(3, 1, 8));
		Assert.assertEquals(2, calculateDiscountQuantity(3, 1, 10));
		Assert.assertEquals(3, calculateDiscountQuantity(3, 1, 13));
		
		
		Assert.assertEquals(0, calculateDiscountQuantity(3, 2, 1));
		Assert.assertEquals(0, calculateDiscountQuantity(3, 2, 3));
		Assert.assertEquals(1, calculateDiscountQuantity(3, 2, 4));
		Assert.assertEquals(2, calculateDiscountQuantity(3, 2, 5));
		Assert.assertEquals(2, calculateDiscountQuantity(3, 2, 6));
		Assert.assertEquals(3, calculateDiscountQuantity(3, 2, 9));
		Assert.assertEquals(4, calculateDiscountQuantity(3, 2, 10));
	}
}
