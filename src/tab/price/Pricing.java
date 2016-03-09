package tab.price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import tab.price.data.SimpleDataManager;
import tab.price.format.DefaultOutputFormatter;
import tab.price.parse.JSONInputParser;

public class Pricing {

	public static void main(String[] args) throws IOException {

		PricingService pricingService = new PricingService();
		pricingService.setInputParser(new JSONInputParser());
		pricingService.setOutputFormatter(new DefaultOutputFormatter());

		PricingEngine pricingEngine = new PricingEngine();
		SimpleDataManager dataManager = new SimpleDataManager();
		pricingEngine.setDataManager(dataManager);
		pricingService.setPricingEngine(pricingEngine);

		Scanner scanner = new Scanner(System.in, "UTF-8");
		while (true) {
			String in = scanner.next();
			if ("exit".equalsIgnoreCase(in)) {
				break;
			}else{
				String out = null;
				try {
					out = pricingService.price(in);
				} catch (Exception e) {
					out = "非法录入，退出请输入“exit”";
				}
				System.out.println(out);
			}
		}
	}
}
