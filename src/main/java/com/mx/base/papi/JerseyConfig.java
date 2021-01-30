package com.mx.base.papi;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.mx.base.papi.rates.RatesEndPoint;
import com.mx.base.papi.stronger.StrongerEndPoint;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        
        register(HelloService.class);
        register(ReverseService.class);
        register(RatesEndPoint.class);
        register(StrongerEndPoint.class);
    }
}