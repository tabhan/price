package tab.price.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericGroovyXmlContextLoader;

import tab.price.PricingService;
import tab.price.data.SimpleDataManager;

/**
 * Use spring framework as container.
 * 
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:pricing.groovy", loader = GenericGroovyXmlContextLoader.class)
public class TestSpringPricingService {
	@Resource
	private PricingService pricingService;
	@Resource
	private SimpleDataManager dataManager;

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
		dataManager.setLoadBuyMgetN(true);
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
		dataManager.setLoadRate(true);
		dataManager.setLoadBuyMgetN(false);
		dataManager.loadPromotion();
		doTest("rate.in", "rate.out");
	}

	@Test
	public void testPricing() throws IOException {
		dataManager.setLoadRate(true);
		dataManager.setLoadBuyMgetN(true);
		dataManager.loadPromotion();
		doTest("pricing.in", "pricing.out");
	}
}
