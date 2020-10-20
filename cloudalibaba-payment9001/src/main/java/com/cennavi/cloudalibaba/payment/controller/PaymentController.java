package com.cennavi.cloudalibaba.payment.controller;

import com.cennavi.cloudalibaba.payment.service.ITaskService;
import com.cennavi.cloudalibaba.payment.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
public class PaymentController {
    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    ITaskService taskService;
    @Resource
    PaymentService paymentService;

    /**
     * 返回服务IP，端口
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry, serverPort: " + serverPort + "\t id" + id;
    }

    /**
     * 查询配置文件信息
     * @return
     */
    @GetMapping("/payment/config/info")
    public String getConfigInfo(){
        return configInfo;
    }

    /**
     * 数据库连接测试，查询战法分析任务
     * @return
     */
    @GetMapping("/payment/task")
    public Object getTask(){

        return taskService.getTask();
    }

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id){

        log.info(Thread.currentThread().getName() + ":" +new Date());
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Integer id){


        return paymentService.paymentInfo_TimeOut(id);
    }

    @GetMapping("/payment/hystrix/break/{id}")
    public String paymentBreak(@PathVariable("id") Integer id){


        return paymentService.paymentCircuitBreaker(id);
    }

    @GetMapping("/payment/hystrix/flowlimit/{id}")
    public String paymentFlowlimit(@PathVariable("id") Integer id){


        return paymentService.paymentFlowlimit(id);
    }



}
