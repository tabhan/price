package tab.price.pojo;

public class Product {
	/**
	 * Code of product.
	 */
	private String productId;
	/**
	 * Price.
	 */
	private double perUnitPrice;
	/**
	 * Name.
	 */
	private String name;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getPerUnitPrice() {
		return perUnitPrice;
	}

	public void setPerUnitPrice(double perUnitPrice) {
		this.perUnitPrice = perUnitPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
