package com.mkh.gateway.gatewayclient1.controller;

import com.mkh.gateway.gatewayclient1.config.DynamicConfig;
import com.mkh.gateway.gatewayclient1.config.StaticConfig;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/foos")
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private StaticConfig staticConfig;
    private DynamicConfig dynamicConfig;
    private Environment environment;

    public HomeController(StaticConfig staticConfig, DynamicConfig dynamicConfig, Environment environment) {
        this.staticConfig = staticConfig;
        this.dynamicConfig = dynamicConfig;
        this.environment = environment;
    }

    @GetMapping("/home")
    public String home(@RequestHeader(value="foo") String header , @RequestParam Map<String, String> data) {
        System.out.println(environment.getProperty("local.server.port"));
        System.out.println("header : " + header);
        System.out.println("data : " + data.get("data1"));
        System.out.println("data : " + data.get("data2"));

        return "home";
    }

    @HystrixCommand
    @GetMapping("/config")
    public String config() {
        String config = "static : " + staticConfig.staticConfig() + " , dynamic : " + dynamicConfig.dynamicConfig();
        return config;
    }

    @GetMapping("/hystrixTest")
    @HystrixCommand(fallbackMethod = "fallback")
    public String hystrix(@RequestParam String path) {
        log.debug("path : " + path);

        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/"+path, String.class);

        if(entity.getStatusCode() == HttpStatus.OK)
            return entity.getBody();

        throw new RuntimeException("not Ok");
    }

    private String fallback(String path) {
        return "call fallback method : " + path ;
    }
}
