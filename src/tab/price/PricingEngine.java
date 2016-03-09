package tab.price;

import java.util.Comparator;
import java.util.List;

import tab.price.data.DataManager;
import tab.price.pojo.DiscountInfo;
import tab.price.pojo.Item;
import tab.price.pojo.Order;
import tab.price.pojo.Product;
import tab.price.promotion.Promotion;

/**
 * Pricing Engine that prices items & order
 * 
 * @author Administrator
 *
 */
public class PricingEngine {

	private Comparator<Promotion> promotionComparator = new DefaultPromotionComparator();
	private DataManager dataManager;

	/**
	 * Price order.
	 * 
	 * @param order
	 *            order instance
	 */
	public void price(Order order) {

		// query price
		queryProductInfo(order);

		// apply promotions
		applyPromotions(order);

		// calc sub total
		claculateItemAmount(order);
	}

	/**
	 * calculate total amount of items.
	 * 
	 * @param order
	 *            order instance
	 */
	public void claculateItemAmount(Order order) {

		double orderAmount = 0;
		for (Item item : order.getItems().values()) {

			// sum of per-unit price
			item.setAmount(item.getPerUnitPrice() * item.getQuantity());

			if (item.getDiscountInfo() != null) {
				DiscountInfo discountInfo = item.getDiscountInfo();

				double discountAmount = discountInfo.getPerUnitDiscount() * discountInfo.getQuantiy();
				item.setAmount(item.getAmount() - discountAmount);
			}

			orderAmount += item.getAmount();
		}

		order.setAmount(orderAmount);

	}

	/**
	 * Query products from data manager. Set values to properties in item.
	 * 
	 * @param order
	 *            order instance
	 */
	public void queryProductInfo(Order order) {
		if (order.getItems() != null) {
			for (Item item : order.getItems().values()) {
				queryProductInfo(item);
			}
		}

	}

	/**
	 * Query product from data manager. Set values to properties in item.
	 * 
	 * @param item
	 *            item instance
	 */
	public void queryProductInfo(Item item) {
		Product product = getDataManager().getProduct(item.getProductId());
		if (product != null) {
			item.setPerUnitPrice(product.getPerUnitPrice());
			item.setName(product.getName());
		}
	}

	/**
	 * apply promotions to items.
	 * 
	 * @param order
	 *            order instance
	 */
	public void applyPromotions(Order order) {
		List<Promotion> promotions = getDataManager().getAllPromotions();
		if (promotions != null) {

			// sort
			sortPromotions(promotions);

			for (Promotion promotion : promotions) {
				applyPromotion(order, promotion);
			}
		}
	}

	/**
	 * Check if the promotion can be applied to order. Apply discount.
	 * 
	 * @param order
	 *            order instance
	 * @param promotion
	 *            promotion
	 */
	public void applyPromotion(Order order, Promotion promotion) {
		promotion.applyPromotion(order);

	}

	/**
	 * sort promotions with priority property.
	 * 
	 * @param promotions
	 */
	public void sortPromotions(List<Promotion> promotions) {

		promotions.sort(getPromotionComparator());

	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public Comparator<Promotion> getPromotionComparator() {
		return promotionComparator;
	}

	public void setPromotionComparator(Comparator<Promotion> promotionComparator) {
		this.promotionComparator = promotionComparator;
	}

	/**
	 * Default comparator use priority property to compare promotions.
	 * 
	 * @author Tab
	 *
	 */
	public static class DefaultPromotionComparator implements Comparator<Promotion> {

		@Override
		public int compare(Promotion o1, Promotion o2) {
			int p1 = 0;
			int p2 = 0;
			if (o1 != null) {
				p1 = o1.getPriority();
			}
			if (o2 != null) {
				p2 = o2.getPriority();
			}

			return Integer.compare(p1, p2);
		}

	}

}
