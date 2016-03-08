package tab.price;

import tab.price.pojo.Order;

/**
 * Pricing Engine that prices items & order
 * 
 * @author Administrator
 *
 */
public interface PricingEngine {
	/**
	 * Price order.
	 * @param order order instance
	 */
	void price(Order order);
}
