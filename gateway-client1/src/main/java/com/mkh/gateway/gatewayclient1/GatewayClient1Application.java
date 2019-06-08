package com.mkh.gateway.gatewayclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
public class GatewayClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(GatewayClient1Application.class, args);
    }

}
