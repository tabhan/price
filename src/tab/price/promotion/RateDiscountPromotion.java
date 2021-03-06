package tab.price.promotion;

import tab.price.pojo.DiscountInfo;
import tab.price.pojo.Item;

public class RateDiscountPromotion extends ItemPromotion {
	private double discountRate = 0;

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	@Override
	public void discount(Item item) {
		// apply discount
		double perUnitPrice = item.getPerUnitPrice();

		double perUnitDiscount = 0;
		if (getDiscountRate() >= 1) {
			perUnitDiscount = perUnitPrice;
		} else if (getDiscountRate() > 0) {
			perUnitDiscount = perUnitPrice * (getDiscountRate());
		}

		DiscountInfo discountInfo = createDiscountInfo();
		discountInfo.setQuantiy(item.getQuantity());
		discountInfo.setPerUnitDiscount(perUnitDiscount);

		item.setDiscountInfo(discountInfo);

	}

}
