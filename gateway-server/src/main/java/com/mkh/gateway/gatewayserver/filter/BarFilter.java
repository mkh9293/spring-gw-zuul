package com.mkh.gateway.gatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class BarFilter extends ZuulFilter {
    /**
     * 필터 사용여부
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        System.out.println(context.getRequest().getRequestURI());
        System.out.println(context.getRequest().getRequestURL());
        System.out.println(context.getRequest().getHeaderNames());
        System.out.println(context.getRequest().getPathInfo());
        System.out.println(context.get("bar-api"));
        return "bar-api".equals(context.get("bar-api"));
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
    public Object run() throws ZuulException {
        System.out.println("bar~~~~~~~~~~~~");
        RequestContext context = new RequestContext().getCurrentContext();
        context.addZuulRequestHeader("bar", "foo");
//        context.set
        return null;
    }
}
