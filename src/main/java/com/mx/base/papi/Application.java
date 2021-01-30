package com.mx.base.papi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mx.base.papi.services.ExchangeRatesService;
import com.mx.base.papi.services.ExchangeRatesServiceImpl;

@Configuration
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public ExchangeRatesService getExchangeRatesService() {
		return new ExchangeRatesServiceImpl();
	}
}