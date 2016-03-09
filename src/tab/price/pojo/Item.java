package tab.price.pojo;

public class Item extends Product {
	/**
	 * Item quantity.
	 */
	private long quantity;
	/**
	 * Total amount of this product.(Per-unit, quantity, discount).
	 */
	private double amount;
	/**
	 * Discount detail info.
	 */
	private DiscountInfo discountInfo;

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public DiscountInfo getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(DiscountInfo discountInfo) {
		this.discountInfo = discountInfo;
	}

}
