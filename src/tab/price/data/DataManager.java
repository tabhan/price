package tab.price.data;

import java.util.List;

import tab.price.pojo.Product;
import tab.price.promotion.Promotion;

/**
 * Provides data.
 * 
 * @author Tab
 *
 */
public interface DataManager {
	/**
	 * Query product.
	 * 
	 * @param productId
	 * @return
	 */
	Product getProduct(String productId);

	/**
	 * Query promotions.
	 * 
	 * @return
	 */
	List<Promotion> getAllPromotions();
}
