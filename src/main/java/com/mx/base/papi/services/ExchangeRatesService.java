package com.mx.base.papi.services;

import com.mx.base.papi.model.ExchangeRatesResponse;

import io.reactivex.Single;

public interface ExchangeRatesService {

	Single<ExchangeRatesResponse> getExchangeRates(String base);
}
