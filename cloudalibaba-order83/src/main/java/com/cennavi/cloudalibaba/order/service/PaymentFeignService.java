package com.cennavi.cloudalibaba.order.service;

import com.cennavi.cloudalibaba.commons.Payment;
import com.cennavi.cloudalibaba.commons.RetObj;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
@FeignClient(value = "nacos-payment-provider")
public interface PaymentFeignService {//, fallback = PaymentFeignFallbackService.class

    @GetMapping(value = "/payment/nacos/{id}")
    String getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/config/info")
    String paymentConfigInfo();

    @GetMapping(value = "/payment/task")
    Map paymentTask();

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentTimeout(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/break/{id}")
    public String paymentBreak(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/flowlimit/{id}")
    String paymentFlowlimit(@PathVariable("id")Integer id);
}
