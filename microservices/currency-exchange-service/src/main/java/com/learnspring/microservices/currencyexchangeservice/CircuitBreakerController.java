package com.learnspring.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController

public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
	//Retry Method
	//@Retry(name = "sample-api", fallbackMethod = "hardCoded")
	
	
	// Circuit Breaker Pattern
	@CircuitBreaker(name = "sample-api", fallbackMethod = "hardCoded")
	
	public String sampleApi() {
		logger.info("Sample API Recieved");
		new RestTemplate().getForEntity("http://localhost:8080/sampleapi", CircuitBreakerController.class);
		return "Sample API";
	}
	
	public String hardCoded(Exception ex) {
		return "Hello ";
	}
}
