package tab.price.format;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tab.price.pojo.DiscountInfo;
import tab.price.pojo.Item;
import tab.price.pojo.Order;
import tab.price.promotion.BuyMgetNPromotion;
import tab.price.promotion.Promotion;
import tab.price.promotion.RateDiscountPromotion;

/**
 * Simple/Default formatter that out contents directly.
 * @author Tab
 *
 */
public class DefaultOutputFormatter implements OutputFormatter {

	private String lineSeparator = "\n";
	private Format priceFormat = new DecimalFormat("0.00(元)");

	@Override
	public String format(Order order) {

		StringBuilder b = new StringBuilder();

		b.append("***<没钱赚商店>购物清单***");
		b.append(getLineSeparator());

		Map<Promotion, List<Item>> buyMgetN = new LinkedHashMap<>();

		for (Item item : order.getItems().values()) {
			b.append("名称：");
			b.append(item.getName());
			b.append("，数量：");
			b.append(item.getQuantity());
			b.append(item.getUnit());
			b.append("，单价：");
			b.append(formatPrice(item.getPerUnitPrice()));
			b.append("，小计：");
			b.append(formatPrice(item.getAmount()));
			if (item.getDiscountInfo() != null) {
				DiscountInfo discountInfo = item.getDiscountInfo();
				Promotion promotion = discountInfo.getPromotion();

				if (promotion instanceof RateDiscountPromotion) {
					b.append("，节省");
					b.append(formatPrice(discountInfo.getPerUnitDiscount() * discountInfo.getQuantiy()));
				} else if (promotion instanceof BuyMgetNPromotion) {
					List<Item> itemList = buyMgetN.get(promotion);
					if (itemList == null) {
						itemList = new ArrayList<>();
						buyMgetN.put(promotion, itemList);
					}
					itemList.add(item);
				}
			}
			b.append(getLineSeparator());
		}
		b.append("----------------------");
		b.append(getLineSeparator());
		outputBuyMgetN(buyMgetN, b);
		outputOrderSummary(order, b);
		b.append("**********************");
		b.append(getLineSeparator());
		return b.toString();
	}

	/**
	 * Item discounted by "buyMgetN" come out after all items.   
	 * @param buyMgetN all items discounted by "buyMgetN" promotions.
	 * @param b string builder
	 */
	private void outputBuyMgetN(Map<Promotion, List<Item>> buyMgetN, StringBuilder b) {
		for (Entry<Promotion, List<Item>> entry : buyMgetN.entrySet()) {
			b.append(entry.getKey().getName());
			b.append("商品：");
			b.append(getLineSeparator());
			for (Item item : entry.getValue()) {
				b.append("名称：");
				b.append(item.getName());
				b.append("，数量：");
				b.append(item.getDiscountInfo().getQuantiy());
				b.append(item.getUnit());
				b.append(getLineSeparator());
			}
			b.append("----------------------");
			b.append(getLineSeparator());
		}
	}

	/**
	 * Output order summary.
	 * @param order order instance
	 * @param b string builder
	 */
	private void outputOrderSummary(Order order, StringBuilder b) {
		b.append("总计：");
		b.append(formatPrice(order.getAmount()));
		b.append(getLineSeparator());
		if (order.getItemDiscount() > 0) {
			b.append("节省：");
			b.append(formatPrice(order.getItemDiscount()));
			b.append(getLineSeparator());
		}
	}

	public String formatPrice(double price) {
		return priceFormat.format(price);
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

}
