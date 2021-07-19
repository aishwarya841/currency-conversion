package com.learnspring.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {
	
	
	@GetMapping(path="currency-conversion/from/{from}/to/{to}/quantity/{qunatity}")
	public CurrencyConversion canculateCurrencyConverted() {
		return new CurrencyConversion(1000l,"USD","INR",BigDecimal.valueOf(65),BigDecimal.valueOf(10),BigDecimal.valueOf(650),"");
	}

}
