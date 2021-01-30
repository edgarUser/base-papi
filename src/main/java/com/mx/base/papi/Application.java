package com.mx.base.papi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mx.base.papi.exceptions.CurrencyNotFoundMapper;
import com.mx.base.papi.exceptions.InternalErrorMapper;
import com.mx.base.papi.rates.services.ExchangeRatesService;
import com.mx.base.papi.rates.services.ExchangeRatesServiceImpl;
import com.mx.base.papi.stronger.services.StrongerService;
import com.mx.base.papi.stronger.services.StrongerServiceImpl;

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

	@Bean
	public StrongerService getStrongerService() {
		return new StrongerServiceImpl();
	}

	@Bean
	public CurrencyNotFoundMapper getCurrencyNotFoundMapper() {
		return new CurrencyNotFoundMapper();
	}

	@Bean
	public InternalErrorMapper getInternalErrorMapper() {
		return new InternalErrorMapper();
	}

}