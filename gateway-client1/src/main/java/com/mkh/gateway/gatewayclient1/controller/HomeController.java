package com.mkh.gateway.gatewayclient1.controller;

import com.mkh.gateway.gatewayclient1.config.DynamicConfig;
import com.mkh.gateway.gatewayclient1.config.StaticConfig;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/foos")
public class HomeController {

    private StaticConfig staticConfig;
    private DynamicConfig dynamicConfig;

    public HomeController(StaticConfig staticConfig, DynamicConfig dynamicConfig) {
        this.staticConfig = staticConfig;
        this.dynamicConfig = dynamicConfig;
    }

    @GetMapping("/home")
    public String home(@RequestHeader(value="foo") String header , @RequestParam Map<String, String> data) {
        System.out.println("header : " + header);
        System.out.println("data : " + data.get("data1"));
        System.out.println("data : " + data.get("data2"));

        return "home";
    }

    @GetMapping("/config")
    public String config() {
        String config = "static : " + staticConfig.staticConfig() + " , dynamic : " + dynamicConfig.dynamicConfig();
        return config;
    }
}
