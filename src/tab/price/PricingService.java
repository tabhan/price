package tab.price;

import tab.price.format.OutputFormatter;
import tab.price.parse.InputParser;

/**
 * It¡¯s a service that takes JSON text as input and returns detail price as out
 * put.
 * 
 * @author Tab
 *
 */
public class PricingService {

	/**
	 * parser.
	 */
	private InputParser inputParser;
	/**
	 * formatter.
	 */
	private OutputFormatter outputFormatter;

	/**
	 * Price products
	 * 
	 * @param json
	 *            contains product id/quantity
	 * @return Price detail
	 */
	public String price(String input) {
		return null;
	}

	public InputParser getInputParser() {
		return inputParser;
	}

	public void setInputParser(InputParser inputParser) {
		this.inputParser = inputParser;
	}

	public OutputFormatter getOutputFormatter() {
		return outputFormatter;
	}

	public void setOutputFormatter(OutputFormatter outputFormatter) {
		this.outputFormatter = outputFormatter;
	}

}
