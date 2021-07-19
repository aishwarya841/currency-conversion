package com.learnspring.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeProxy proxyFeign;
	
	
	//using Rest template
	
	@GetMapping(path="currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion canculateCurrencyConverted(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		
		ResponseEntity<CurrencyConversion> response =   new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		
		CurrencyConversion currency = response.getBody();
		
		return new CurrencyConversion(currency.getId(),from,to,quantity, currency.getConversionMultiple(),quantity.multiply(currency.getConversionMultiple()),"");
	}
	
	//using feign
	@GetMapping(path="currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion canculateCurrencyConvertedFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversion currency = proxyFeign.retrieveExchangeValue(from, to);
		return new CurrencyConversion(currency.getId(),from,to,quantity, currency.getConversionMultiple(),quantity.multiply(currency.getConversionMultiple()), currency.getEnvironment());
		
		
	}

}
