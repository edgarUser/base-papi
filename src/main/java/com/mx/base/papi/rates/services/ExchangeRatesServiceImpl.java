package com.mx.base.papi.rates.services;

import com.mx.base.papi.model.ExchangeRatesResponse;
import com.mx.base.papi.rates.helper.ExchangeRateResponseHelper;

import io.reactivex.Single;

public class ExchangeRatesServiceImpl implements ExchangeRatesService {
	
	public Single<ExchangeRatesResponse> getExchangeRates(final String base) {
		
		return ExchangeRateResponseHelper.getExchangeRates(base);
	}	
}
