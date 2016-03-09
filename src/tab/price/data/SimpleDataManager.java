package tab.price.data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import tab.price.pojo.Product;
import tab.price.promotion.BuyMgetNPromotion;
import tab.price.promotion.Promotion;
import tab.price.promotion.RateDiscountPromotion;

public class SimpleDataManager implements DataManager {

	private Map<String, Product> productMap = new HashMap<>();

	public SimpleDataManager() {
		Product[] products = readFromResource("products.json", Product[].class);
		if (products != null) {
			for (Product product : products) {
				productMap.put(product.getProductId(), product);
			}
		}

	}

	private <E> E readFromResource(String name, Class<E> clz) {
		Gson gson = new Gson();
		InputStream in = this.getClass().getResourceAsStream(name);
		Reader reader;
		try {
			reader = new InputStreamReader(in, "iso-8859-1");
			return gson.fromJson(reader, clz);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product getProduct(String productId) {
		return productMap.get(productId);
	}

	@Override
	public List<Promotion> getAllPromotions() {

		RateDiscountPromotion[] rateDiscounts = readFromResource("rateDiscount.json", RateDiscountPromotion[].class);
		BuyMgetNPromotion[] buyMgetNs = readFromResource("buyMgetN.json", BuyMgetNPromotion[].class);

		ArrayList<Promotion> promtions = new ArrayList<>();

		promtions.addAll(Arrays.asList(rateDiscounts));
		promtions.addAll(Arrays.asList(buyMgetNs));

		return promtions;
	}

}
