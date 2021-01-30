package com.mx.base.papi.stronger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;
import org.springframework.beans.factory.annotation.Autowired;

import com.mx.base.papi.stronger.responses.StrongerResponse;
import com.mx.base.papi.stronger.services.StrongerService;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@Path("stronger/{baseCurrency}/{counterCurrency}")
public class StrongerEndPoint {

	@Autowired
	private StrongerService strongerService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getRates(@Suspended final AsyncResponse async, @PathParam("baseCurrency") final String baseCurrency,
			@PathParam("counterCurrency") final String counterCurrency) {

		final StrongerResponse response = new StrongerResponse();
		final CountDownLatch outerLatch = new CountDownLatch(1);

		strongerService.isStronger(baseCurrency, counterCurrency).subscribe(new SingleObserver<Boolean>() {

			public void onSubscribe(Disposable d) {
			}

			public void onSuccess(Boolean result) {
				response.setStronger(result);
				outerLatch.countDown();
			}

			public void onError(Throwable e) {
				outerLatch.countDown();
			}
		});

		try {
			if (!outerLatch.await(10, TimeUnit.SECONDS)) {
				throw new Exception();
			}
		} catch (Exception e) {

		}

		async.resume(response);
	}

}
