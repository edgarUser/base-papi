package com.mx.base.papi.rates.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.mx.base.papi.model.ExchangeRatesResponse;
import com.mx.base.papi.rates.adapter.ExchangeRatesAdapter;

import io.reactivex.Single;
public class ExchangeRatesServiceImpl implements ExchangeRatesService {
	
	private ExchangeRatesAdapter exchangeRatesAdapter;
	
	@Autowired
	public ExchangeRatesServiceImpl(ExchangeRatesAdapter exchangeRatesAdapter) {
		
		this.exchangeRatesAdapter = exchangeRatesAdapter;
	}
	
	public Single<ExchangeRatesResponse> getExchangeRates(final String base) {
		
		return exchangeRatesAdapter.getExchangeRates(base);
	}	
}
