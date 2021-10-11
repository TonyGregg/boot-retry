package com.genil.learning.retry.controller;

import com.genil.learning.retry.service.RestInvokerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/retry")
public class RetryController {
    @Autowired
    RestInvokerService restInvokerService;
    @GetMapping("/hello/{name}")
    public String doRetry(@PathVariable(name = "name") String name) {
        log.info("Inside retry controller. Name passed {} ", name);
        String response = restInvokerService.invokeHelloApi(name);
        log.info("Response {} ", response);
        return  response;
    }
}
