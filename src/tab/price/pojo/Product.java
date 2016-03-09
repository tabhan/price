package tab.price.pojo;

/**
 * Product info.
 * 
 * @author Tab
 *
 */
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
	/**
	 * Unit name.
	 */
	private String unit;

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
