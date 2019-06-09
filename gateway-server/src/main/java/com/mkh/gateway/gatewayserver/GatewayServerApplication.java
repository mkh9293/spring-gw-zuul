package com.mkh.gateway.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * eureka 설정 시, 내부적으로 ribbon을 사용하므로 설정 주석 처리
 */
//@EnableConfigServer
@EnableEurekaClient
//@RibbonClients({
//        @RibbonClient(name="gateway-client1")
//})
@EnableZuulProxy
@SpringBootApplication
public class GatewayServerApplication {

//    public static final String app_location = "spring.config.location=classpath:application.yml";

//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
//        new SpringApplicationBuilder(GatewayServerApplication.class)
//                .profiles(app_location)
//                .run(args);
         SpringApplication.run(GatewayServerApplication.class, args);
    }

}
