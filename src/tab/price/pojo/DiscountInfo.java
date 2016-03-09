package tab.price.pojo;

import tab.price.promotion.Promotion;

/**
 * Discount info of item.
 * 
 * @author Tab
 *
 */
public class DiscountInfo {
	/**
	 * Discount amount for one unit of this item.
	 */
	private double perUnitDiscount;
	/**
	 * How many products applied discount.
	 */
	private long quantiy;
	/**
	 * Psromotion.
	 */
	private Promotion promotion;

	public double getPerUnitDiscount() {
		return perUnitDiscount;
	}

	public void setPerUnitDiscount(double perUnitDiscount) {
		this.perUnitDiscount = perUnitDiscount;
	}

	public long getQuantiy() {
		return quantiy;
	}

	public void setQuantiy(long quantiy) {
		this.quantiy = quantiy;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}
