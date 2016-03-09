package tab.price.promotion;

import tab.price.pojo.DiscountInfo;
import tab.price.pojo.Item;
import tab.price.pojo.Order;

public class BuyMgetNPromotion extends ItemPromotion {
	private long m = Long.MAX_VALUE;
	private long n = 0;

	@Override
	public boolean qualified(Order order) {
		return super.qualified(order) && order.getItems().get(getProductId()).getQuantity() > getM();
	}

	@Override
	public void discount(Order order) {
		// apply discount
		Item item = order.getItems().get(getProductId());
		double perUnitPrice = item.getPerUnitPrice();
		double perUnitDiscount = perUnitPrice;

		long discountQuantity = calculateDiscountQuantity(item.getQuantity());

		DiscountInfo discountInfo = createDiscountInfo();
		discountInfo.setQuantiy(discountQuantity);
		discountInfo.setPerUnitDiscount(perUnitDiscount);
		item.setDiscountInfo(discountInfo);

	}

	public long calculateDiscountQuantity(long itemQuantity) {

		// if the quantity in order is enough to gift
		long quantity = itemQuantity / (getM() + getN()) * getN();

		// if quantity is not enough
		long exceeded = itemQuantity % (getM() + getN());
		if (exceeded > getM()) {

			// use part of exceeded quantities as gift.
			// buy 3 get 2. 4 in order, actually get 1 free.
			quantity += exceeded - getM();
		}

		return quantity;
	}

	public long getM() {
		return m;
	}

	public void setM(long m) {
		this.m = m;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

}
