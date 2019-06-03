package com.mkh.gateway.gatewayclient1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class DynamicConfig {
    @Value("${application.service.name}")
    private String name;

    public String dynamicConfig() {
        return name;
    }
}
