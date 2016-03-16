package tab.price;

import java.util.Scanner;

import org.springframework.context.support.GenericGroovyApplicationContext;

public class Pricing {

	public static void main(String[] args) {

		GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("classpath:pricing.groovy");
		PricingService pricingService = (PricingService) context.getBean("pricingService");

		Scanner scanner = new Scanner(System.in, "UTF-8");
		while (true) {
			String in = scanner.next();
			if ("exit".equalsIgnoreCase(in)) {
				break;
			} else {
				String out = null;
				try {
					out = pricingService.price(in);
				} catch (Exception e) {
					out = "非法录入，退出请输入“exit”";
				}
				System.out.println(out);
			}
		}

		scanner.close();
		context.close();
	}
}
