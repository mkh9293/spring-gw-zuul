package com.mkh.gateway.gatewayserver.controller;

import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController implements ErrorController {
    private static final String ERROR_URL = "/error";

    @RequestMapping(value = ERROR_URL)
    public ResponseEntity<String> error() {
        RequestContext context = RequestContext.getCurrentContext();
        Object error = ExceptionUtils.getRootCause((Exception) context.get("throwable"));

        if(error == null) {
            return new ResponseEntity<String>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        if(error instanceof Exception) {
            return new ResponseEntity<String>("SERVICE_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE);
        }


        return new ResponseEntity<String>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getErrorPath() {
        return ERROR_URL;
    }
}
