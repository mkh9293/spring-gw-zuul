package com.mkh.gateway.gatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.micrometer.core.ipc.http.HttpSender;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

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
        System.out.println(context.getRequest().getRequestURI());
        System.out.println(context.getRequest().getRequestURL());
        return requestUri.startsWith("/foos");
//        return "foo-api".equals(context.get("proxy"));
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
        System.out.println("foo~~~~~~~~~~~~");
        RequestContext context = new RequestContext().getCurrentContext();

//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                // customize
//                .build();
//
//        HttpServletRequest request = context.getRequest();
//
//        String method = request.getMethod();
//
//        String uri = this.helper.buildZuulRequestURI(request);
//
//        Headers.Builder headers = new Headers.Builder();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            Enumeration<String> values = request.getHeaders(name);
//
//            while (values.hasMoreElements()) {
//                String value = values.nextElement();
//                headers.add(name, value);
//            }
//        }
//
//        InputStream inputStream = null;
//        try {
//            inputStream = request.getInputStream();
//        } catch (IOException e) {
//            e.getMessage();
//        }
//
//
//        RequestBody requestBody = null;
//        if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
//            MediaType mediaType = null;
//            if (headers.get("Content-Type") != null) {
//                mediaType = MediaType.parse(headers.get("Content-Type"));
//            }
//            requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
//        }
//
//        Request.Builder builder = new Request.Builder()
//                .headers(headers.build())
//                .url(uri)
//                .method(method, requestBody);
//
//        Response response = httpClient.newCall(builder.build()).execute();
//
//        LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
//
//        for (Map.Entry<String, List<String>> entry : response.header().toMultimap().entrySet()) {
//            responseHeaders.put(entry.getKey(), entry.getValue());
//        }
        context.addZuulRequestHeader("foo", "bar");
//        context.set
        return null;


    }
}
