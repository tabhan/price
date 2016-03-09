package tab.price.data;

import java.util.List;

import tab.price.pojo.Product;
import tab.price.promotion.Promotion;

public interface DataManager {
	Product getProduct(String productId);

	List<Promotion> getAllPromotions();
}
