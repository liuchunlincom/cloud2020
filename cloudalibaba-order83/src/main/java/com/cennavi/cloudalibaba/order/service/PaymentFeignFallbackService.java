package com.cennavi.cloudalibaba.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
public class PaymentFeignFallbackService {

    String paymentTimeout(@PathVariable("id") Integer id){
        return "paymentTimeoutFallback===========";
    }
}
