package com.learnspring.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@GetMapping(path="/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveChangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currency = new CurrencyExchange(1000l, from, to, BigDecimal.valueOf(50));
		String port = environment.getProperty("local.server.port");
		currency.setEnvironment(port);
		return currency;
	}
	
	
	
	
}
