package tab.price.test;

import org.junit.Assert;
import org.junit.Test;

import tab.price.parse.InputParser;
import tab.price.parse.JSONInputParser;
import tab.price.pojo.Order;

public class TestParser {

	@Test
	public void testJSONInputParser() {

		String input = "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";

		InputParser parser = new JSONInputParser();

		Order order = parser.parse(input);

		Assert.assertNotNull(order);

	}
}
