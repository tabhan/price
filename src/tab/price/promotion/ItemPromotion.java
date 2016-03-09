package tab.price.promotion;

import tab.price.pojo.Item;
import tab.price.pojo.Order;

public abstract class ItemPromotion extends Promotion {
	private String productId;

	public boolean qualified(Order order) {
		if (order == null) {
			return false;
		}

		Item item = order.getItems().get(productId);
		return item != null && item.getDiscountInfo() == null;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
