package com.mkh.gateway.gatewayclient2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("bars")
@RestController
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home123";
    }

    @GetMapping("/rest")
    public String rest() {
        return "rest";
    }
}
