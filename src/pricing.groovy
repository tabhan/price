
beans{

	inputParser(tab.price.parse.JSONInputParser)
	
	outputFormatter(tab.price.format.DefaultOutputFormatter)
	
	dataManager(tab.price.data.SimpleDataManager)
	
	
	pricingEngine(tab.price.PricingEngine){
		dataManager=dataManager
	}
	
	pricingService(tab.price.PricingService){ bean ->
		bean.scope="singleton"
		inputParser=inputParser
		outputFormatter=outputFormatter
		pricingEngine=pricingEngine
	}
}