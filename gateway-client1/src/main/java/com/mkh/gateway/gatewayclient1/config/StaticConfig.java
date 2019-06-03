package com.mkh.gateway.gatewayclient1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StaticConfig {

    @Value("${application.service.name}")
    private String appName;

    public String staticConfig() {
        return appName;
    }
}
