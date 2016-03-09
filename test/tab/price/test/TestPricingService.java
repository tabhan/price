package tab.price.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tab.price.PricingEngine;
import tab.price.PricingService;
import tab.price.data.SimpleDataManager;
import tab.price.format.DefaultOutputFormatter;
import tab.price.parse.JSONInputParser;

public class TestPricingService {

	private PricingService pricingService;
	private SimpleDataManager dataManager;

	@Before
	public void init() {
		pricingService = new PricingService();
		pricingService.setInputParser(new JSONInputParser());
		pricingService.setOutputFormatter(new DefaultOutputFormatter());

		PricingEngine pricingEngine = new PricingEngine();
		dataManager = new SimpleDataManager();
		pricingEngine.setDataManager(dataManager);
		pricingService.setPricingEngine(pricingEngine);
	}

	private String readFile(String fileName) throws IOException {
		InputStream in = this.getClass().getResourceAsStream("data/" + fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();
		while (reader.ready()) {
			stringBuilder.append(reader.readLine());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public void doTest(String inFile, String outFile) throws IOException {

		String input = readFile(inFile);
		String output = readFile(outFile);
		String result = pricingService.price(input);
		Assert.assertEquals(output, result);

	}

	@Test
	public void testBuy2get1() throws IOException {
		dataManager.setLoadRate(false);
		dataManager.loadPromotion();
		doTest("buy2get1.in", "buy2get1.out");
	}

	@Test
	public void testNoPromo() throws IOException {
		dataManager.setLoadRate(false);
		dataManager.setLoadBuyMgetN(false);
		dataManager.loadPromotion();
		doTest("noPromo.in", "noPromo.out");
	}

	@Test
	public void testRate() throws IOException {
		dataManager.setLoadBuyMgetN(false);
		dataManager.loadPromotion();
		doTest("rate.in", "rate.out");
	}
	
	@Test
	public void testPricing() throws IOException {
		dataManager.loadPromotion();
		doTest("pricing.in", "pricing.out");
	}
}
