package tab.price.pojo;

import tab.price.promotion.Promotion;

public class DiscountInfo {
	private double perUnitDiscount;
	private long quantiy;
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
