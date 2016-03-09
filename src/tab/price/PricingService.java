package tab.price;

import tab.price.format.OutputFormatter;
import tab.price.parse.InputParser;
import tab.price.pojo.Order;

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
	 * pricing engine.
	 */
	private PricingEngine pricingEngine;

	/**
	 * Price products
	 * 
	 * @param json
	 *            contains product id/quantity
	 * @return Price detail
	 */
	public String price(String input) {

		// parse input.
		Order order = getInputParser().parse(input);

		// invoke pricing engine.
		getPricingEngine().price(order);

		// formate output
		return getOutputFormatter().format(order);
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

	public PricingEngine getPricingEngine() {
		return pricingEngine;
	}

	public void setPricingEngine(PricingEngine pricingEngine) {
		this.pricingEngine = pricingEngine;
	}

}
