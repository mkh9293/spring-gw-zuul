package com.mkh.gateway.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableConfigServer
@EnableZuulProxy
@SpringBootApplication
public class GatewayServerApplication {

    public static final String app_location = "spring.config.location=classpath:application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayServerApplication.class)
                .profiles(app_location)
                .run(args);
//         SpringApplication.run(GatewayServerApplication.class, args);
    }

}
