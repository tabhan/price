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

/**
 * Load data from json files in class path.
 * 
 * @author Tab
 *
 */
public class SimpleDataManager implements DataManager {
	/**
	 * products.
	 */
	private Map<String, Product> productMap = new HashMap<>();
	/**
	 * promotions.
	 */
	List<Promotion> promotions = new ArrayList<>();

	private boolean loadBuyMgetN = true;
	private boolean loadRate = true;

	public SimpleDataManager() {
		loadProduct();
		loadPromotion();
	}

	@Override
	public Product getProduct(String productId) {
		return productMap.get(productId);
	}

	@Override
	public List<Promotion> getAllPromotions() {
		return promotions;
	}

	/**
	 * Load products from json file.
	 */
	public void loadProduct() {
		productMap.clear();
		Product[] products = readFromResource("products.json", Product[].class);
		if (products != null) {
			for (Product product : products) {
				productMap.put(product.getProductId(), product);
			}
		}
	}

	/**
	 * Load promotions from json file.
	 */
	public void loadPromotion() {
		promotions.clear();
		if (loadBuyMgetN) {
			BuyMgetNPromotion[] buyMgetNs = readFromResource("buyMgetN.json", BuyMgetNPromotion[].class);
			promotions.addAll(Arrays.asList(buyMgetNs));
		}

		if (loadRate) {
			RateDiscountPromotion[] rateDiscounts = readFromResource("rateDiscount.json",
					RateDiscountPromotion[].class);
			promotions.addAll(Arrays.asList(rateDiscounts));
		}
	}

	private <E> E readFromResource(String name, Class<E> clz) {
		Gson gson = new Gson();
		InputStream in = this.getClass().getResourceAsStream(name);
		Reader reader;
		try {
			reader = new InputStreamReader(in, "UTF-8");
			return gson.fromJson(reader, clz);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isLoadBuyMgetN() {
		return loadBuyMgetN;
	}

	public void setLoadBuyMgetN(boolean loadBuyMgetN) {
		this.loadBuyMgetN = loadBuyMgetN;
	}

	public boolean isLoadRate() {
		return loadRate;
	}

	public void setLoadRate(boolean loadRate) {
		this.loadRate = loadRate;
	}

}
