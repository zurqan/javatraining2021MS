package com.training.clientservice.adapter.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax")
@RefreshScope
public class TaxController {

    @Value("${com.training.tax:20}")
    private int tax;

    @GetMapping
    public int gettingTax(){
        return tax;
    }
}
