package tab.price.promotion;

import tab.price.pojo.DiscountInfo;
import tab.price.pojo.Order;

public abstract class Promotion {
	private String id;
	private int priority = 0;

	public void applyPromotion(Order order) {

		// this promotion is qualified.
		if (qualified(order)) {

			// apply discount
			discount(order);
		}

	}

	public abstract boolean qualified(Order order);

	public abstract void discount(Order order);

	protected DiscountInfo createDiscountInfo() {
		DiscountInfo discountInfo = new DiscountInfo();
		discountInfo.setPromotion(this);
		return discountInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
