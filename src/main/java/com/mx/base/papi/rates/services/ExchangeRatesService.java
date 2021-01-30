package com.mx.base.papi.rates.services;

import com.mx.base.papi.model.ExchangeRatesResponse;

import io.reactivex.Single;

public interface ExchangeRatesService {

	Single<ExchangeRatesResponse> getExchangeRates(String base);
}
