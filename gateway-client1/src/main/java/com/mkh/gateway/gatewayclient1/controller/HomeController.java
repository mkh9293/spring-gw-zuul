package com.mkh.gateway.gatewayclient1.controller;

import com.mkh.gateway.gatewayclient1.config.DynamicConfig;
import com.mkh.gateway.gatewayclient1.config.StaticConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String home() {
        return "home";
    }

    @GetMapping("/config")
    public String config() {
        String config = "static : " + staticConfig.staticConfig() + " , dynamic : " + dynamicConfig.dynamicConfig();
        return config;
    }
}
