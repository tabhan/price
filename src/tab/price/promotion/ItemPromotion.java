package tab.price.promotion;

import java.util.Set;

import tab.price.pojo.Item;
import tab.price.pojo.Order;

public abstract class ItemPromotion extends Promotion {
	private Set<String> productIds;

	@Override
	public void applyPromotion(Order order) {
		Item item = null;
		if (getProductIds() != null) {
			for (String productId : getProductIds()) {
				item = order.getItems().get(productId);
				if (item != null && item.getDiscountInfo() == null) {
					discount(item);
				}
			}
		}
	}

	public abstract void discount(Item item);

	public Set<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(Set<String> productIds) {
		this.productIds = productIds;
	}

}
