package com.cennavi.cloudalibaba.order.controller;

import com.cennavi.cloudalibaba.order.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderNacosController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/nacos/{id}")
    public Object paymentInfo(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/config/info")
    public Object paymentConfigInfo() {

        return paymentFeignService.paymentConfigInfo();
    }

    @GetMapping("/consumer/payment/task")
    public Object paymentTask() {

        paymentFeignService.paymentConfigInfo();

        return paymentFeignService.paymentTask();
    }

    @GetMapping("/payment/hystrix/ok/{id}")
    public Object paymentOk(@PathVariable("id") Integer id){
        return paymentFeignService.paymentOk(id);
    }

    @HystrixCommand
    @GetMapping("/payment/hystrix/timeout/{id}")
    public Object paymentTimeout(@PathVariable("id") Integer id){

        return paymentFeignService.paymentTimeout(id);
    }
    @GetMapping("/payment/hystrix/break/{id}")
    public String paymentBreak(@PathVariable("id") Integer id){
        return paymentFeignService.paymentBreak(id);
    }


    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }
}
