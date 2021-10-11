package com.genil.learning.retry.service;

import com.genil.learning.retry.exception.RemoteServiceNotAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RestInvokerService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${hello.url}")
    String helloUrl;
    @Retryable(
            maxAttemptsExpression = "${hello.maxRetryAttempts}",
            value = Exception.class,
            backoff = @Backoff(delay = 120000)) // 120 seconds (2 minutes)
    public String invokeHelloApi(String name) {
        log.info("API URL {}, request  {} ", helloUrl, name);
        ResponseEntity<String> response = restTemplate.getForEntity(helloUrl + " Mr. " + name, String.class);
        return response.getBody();
    }
//
//    @Override
//    public String getBackendResponseFallback(RuntimeException e) {
//        log.error("Sorry, not successful with {} ", helloUrl);
//        return "Unavailable error";
//    }
}
