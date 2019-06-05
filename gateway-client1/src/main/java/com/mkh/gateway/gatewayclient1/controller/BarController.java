package com.mkh.gateway.gatewayclient1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bars")
public class BarController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
