package com.mx.base.papi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mx.base.papi.exceptions.CurrencyNotFoundMapper;
import com.mx.base.papi.exceptions.InternalErrorMapper;
import com.mx.base.papi.rates.adapter.ExchangeRatesAdapter;
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
	public ExchangeRatesAdapter getExchangeRatesAdapter() {
		return new ExchangeRatesAdapter();
	}

	@Bean
	public ExchangeRatesService getExchangeRatesService() {
		return new ExchangeRatesServiceImpl(getExchangeRatesAdapter());
	}

	@Bean
	public StrongerService getStrongerService() {
		return new StrongerServiceImpl(getExchangeRatesAdapter());
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