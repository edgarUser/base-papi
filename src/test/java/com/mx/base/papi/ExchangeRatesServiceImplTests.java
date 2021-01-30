package com.mx.base.papi;

import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mx.base.papi.exceptions.InternalErrorException;
import com.mx.base.papi.model.ExchangeRatesResponse;
import com.mx.base.papi.rates.adapter.ExchangeRatesAdapter;
import com.mx.base.papi.rates.services.ExchangeRatesServiceImpl;

import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;

public class ExchangeRatesServiceImplTests {

	@Mock ExchangeRatesAdapter mockAdapter;

	private ExchangeRatesServiceImpl sut;
	private String baseCurrency = "EUR";
	private String counterCurrency = "USD";

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		
		sut = new ExchangeRatesServiceImpl(mockAdapter);
	}
	
	@After
	public void tearDown() {
		
		sut = null;
	}
	
	@Test
	public void testHappyPath() {
		
		ExchangeRatesResponse expectedResponse = createResponse();
		
		doReturn(Single.just(expectedResponse)).when(mockAdapter).getExchangeRates(baseCurrency);
		
		TestSubscriber<ExchangeRatesResponse> testSubscriber = new TestSubscriber<ExchangeRatesResponse>();
		
		sut.getExchangeRates(baseCurrency).toFlowable().subscribe(testSubscriber);
		
		testSubscriber.assertValue(expectedResponse);
	}
		
	@Test
	public void testSadPath() {
				
		doReturn(Single.error(new InternalErrorException())).when(mockAdapter).getExchangeRates(baseCurrency);
		
		TestSubscriber<ExchangeRatesResponse> testSubscriber = new TestSubscriber<ExchangeRatesResponse>();
		
		sut.getExchangeRates(baseCurrency).toFlowable().subscribe(testSubscriber);
		
		testSubscriber.assertError(InternalErrorException.class);
	}
	
	@Test
	public void testSubscription() {
		
		doReturn(Single.never()).when(mockAdapter).getExchangeRates(baseCurrency);
		
		TestSubscriber<ExchangeRatesResponse> testSubscriber = new TestSubscriber<ExchangeRatesResponse>();
		
		sut.getExchangeRates(baseCurrency).toFlowable().subscribe(testSubscriber);
		
		testSubscriber.assertSubscribed();
	}
	
	@Test
	public void testComplete() {
		
		ExchangeRatesResponse expectedResponse = createResponse();
		
		doReturn(Single.just(expectedResponse)).when(mockAdapter).getExchangeRates(baseCurrency);
		
		TestSubscriber<ExchangeRatesResponse> testSubscriber = new TestSubscriber<ExchangeRatesResponse>();
		
		sut.getExchangeRates(baseCurrency).toFlowable().subscribe(testSubscriber);
		
		testSubscriber.assertComplete();
	}
	
	private ExchangeRatesResponse createResponse() {
		
		ExchangeRatesResponse response = new ExchangeRatesResponse();
		response.setBase(baseCurrency);
		response.setDate("date");
		
		HashMap<String, BigDecimal> rates = new HashMap<String, BigDecimal>();
		rates.put(counterCurrency, new BigDecimal("1.0661"));
		
		return response;
	}
}
