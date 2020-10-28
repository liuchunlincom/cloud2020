package com.cennavi.cloudalibaba.order.controller;

import com.cennavi.cloudalibaba.order.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public Object paymentOk(@PathVariable("id") Integer id){
        return paymentFeignService.paymentOk(id);
    }

    /**
     * 降级测试
     * @param id
     * @return
     */
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public Object paymentTimeout(@PathVariable("id") Integer id){

        return paymentFeignService.paymentTimeout(id);
    }

    /**
     * 熔断测试
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/break/{id}")
    public String paymentBreak(@PathVariable("id") Integer id){
        return paymentFeignService.paymentBreak(id);
    }

    /**
     * 限流测试
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "flowLimitMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "1")
            })
    @GetMapping("/consumer/payment/hystrix/flowlimit/{id}")
    public String paymentFlowlimit(@PathVariable("id") Integer id){

        log.info("限流测试" + id);
        return "限流测试正常返回" + id;
        //return paymentFeignService.paymentFlowlimit(id);
    }


    /**
     * 限流测试
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "flowLimitMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.timeout.enabled", value = "false"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "false"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1")}
            )
    @GetMapping("/consumer/payment/hystrix/flowlimit2/{id}")
    public String paymentFlowlimit2(@PathVariable("id") Integer id){

        return "限流测试正常返回" + id;
        //return paymentFeignService.paymentFlowlimit(id);
    }



    public String flowLimitMethod(Integer id){
        log.warn(""+id);


        return "限流处理" + id + ":"+System.currentTimeMillis();
    }


    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }
}
