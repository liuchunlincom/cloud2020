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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class OrderSessionController {

    @Resource
    private PaymentFeignService paymentFeignService;
    @Value("${server.port}")
    private Integer port;// 项目端口

    @GetMapping("/consumer/order/session")
    public Object paymentInfo(HttpServletRequest request) {

        return "session一致性测试，port:" + port + ", session:" + request.getSession().getId();
    }

    @GetMapping("/consumer/createSession")
    public String createSession(HttpSession session, String name) {
        session.setAttribute("name", name);
        return "session一致性测试，当前项目端口：" + port + " 当前sessionId :" + session.getId() + "在Session中存入成功！";
    }

    @GetMapping("/consumer/getSession")
    public String getSession(HttpSession session) {

        return "session一致性测试，当前项目端口：" + port + " 当前sessionId :" + session.getId() + " 获取的姓名:" + session.getAttribute("name");

    }


}
