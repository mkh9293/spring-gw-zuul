package com.mkh.gateway.gatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.micrometer.core.ipc.http.HttpSender;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class FooFilter extends ZuulFilter  {

    @Autowired
    private ProxyRequestHelper helper;

    /**
     * 필터 사용여부
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        String requestUri = context.getRequest().getRequestURI();
        return requestUri.startsWith("/foos");
    }

    /**
     * 필터 타입 정의 (pre: 라우팅 전, routing, post: 라우팅 후)
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 필터 우선순위 지정 - 낮을수록 높은 우선순위를 가짐
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 실제 필터 동작 부분.
     */
    @Override
    public Object run() {
        RequestContext context = new RequestContext().getCurrentContext();

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String result = restTemplate.getForObject("http://localhost:8080/bars/home", String.class);
        System.out.println("result : " + result);

        context.addZuulRequestHeader("foo", result);
        Map<String, List<String>> map = new HashMap<>();
        map.put("data1", Arrays.asList("value1"));
        map.put("data2", Arrays.asList("value2"));

        context.setRequestQueryParams(map);
        return null;
    }
}
