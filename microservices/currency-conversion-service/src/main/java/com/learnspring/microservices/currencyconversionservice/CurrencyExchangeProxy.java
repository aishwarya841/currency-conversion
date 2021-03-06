package com.learnspring.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//name would be the name of the application for which we're creating the proxy;
//@FeignClient(name="currency-exchange", url="localhost:8000") 

@FeignClient(name="currency-exchange") // for using with eureka we don't need to add the url
public interface CurrencyExchangeProxy {

	@GetMapping(path="/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
   