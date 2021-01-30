package com.mx.base.papi.rates.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.mx.base.papi.exceptions.CurrencyNotFoundException;
import com.mx.base.papi.exceptions.InternalErrorException;
import com.mx.base.papi.model.ExchangeRatesResponse;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class ExchangeRateResponseHelper {

	private static final String EXCHANGE_RATE_BASE_END_POINT = "http://api.fixer.io/latest?base=%s";

	public static Single<ExchangeRatesResponse> getExchangeRates(final String base) {
		
		return Single.create(new SingleOnSubscribe<ExchangeRatesResponse>() {

			public void subscribe(SingleEmitter<ExchangeRatesResponse> subscriber) {
				
				try {
					String endPoint = String.format(EXCHANGE_RATE_BASE_END_POINT, base);
		    		URL obj = new URL(endPoint);
		    		
		    		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		    		con.setRequestMethod("GET");

		    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    		ExchangeRatesResponse response = ExchangeRateResponseHelper.readRatesFromResponse(in);
		    		subscriber.onSuccess(response);
		    		
				} catch (Exception e) {
					
					subscriber.onError(new InternalErrorException());
				}
			}
		});		
	}	

	public static ExchangeRatesResponse readRatesFromResponse(BufferedReader in) throws Exception {
		
		String inputLine;
		StringBuffer response = new StringBuffer();

		try {
			while ((inputLine = in.readLine()) != null) {
    			response.append(inputLine);
    		}
    		in.close();
    		
    		String responseString = response.toString();
    		Gson gson = new Gson();
    		return gson.fromJson(responseString, ExchangeRatesResponse.class);

		} catch (Exception e) {
			throw new CurrencyNotFoundException();
		} finally {
    		in.close();
		} 
	}
}
