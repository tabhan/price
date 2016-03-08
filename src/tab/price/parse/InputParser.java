package tab.price.parse;

import tab.price.pojo.Order;

/**
 * Parse from input string to Order instance.
 * 
 * @author Tab
 *
 */
public interface InputParser {
	/**
	 * parse order instance from input string.
	 * 
	 * @param input
	 *            input string
	 * @return order instance
	 */
	Order parse(String input);
}
