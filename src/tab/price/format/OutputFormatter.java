package tab.price.format;

import tab.price.pojo.Order;

/**
 * Format order to string as output. 
 * 
 * @author Tab
 *
 */
public interface OutputFormatter {
	/**
	 * format.
	 * @param order order instance
	 * @return output content
	 */
	String format(Order order);
}
