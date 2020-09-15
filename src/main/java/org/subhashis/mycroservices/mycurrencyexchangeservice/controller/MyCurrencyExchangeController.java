package org.subhashis.mycroservices.mycurrencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subhashis.mycroservices.mycurrencyexchangeservice.repository.MyExchangeValueRepository;
import org.subhashis.mycroservices.mycurrencyexchangeservice.model.ExchangeValue;

@RestController
@RequestMapping("/currency-exchange")
public class MyCurrencyExchangeController {

	@Autowired
	private MyExchangeValueRepository repository;

	@Autowired
	private Environment environment;

	@GetMapping("/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = repository
				.findByFromAndTo(from, to)
				.orElseThrow(() -> new IllegalArgumentException("Invalid source or target currency specified"));
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}

	@GetMapping("/hello/{name}")
	public String slowGreeting(@PathVariable("name") String name) throws InterruptedException {
		System.out.println("Slowly greets " + name);
		Thread.sleep(5000);
		return "Hello " + name;
	}
}

