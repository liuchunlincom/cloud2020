package com.cennavi.cloudalibaba.order.controller;

import com.cennavi.cloudalibaba.commons.CommonResult;
import com.cennavi.cloudalibaba.order.model.Order;
import com.cennavi.cloudalibaba.order.service.AccountService;
import com.cennavi.cloudalibaba.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;


    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        order.setId(UUID.randomUUID().toString().replaceAll("-",""));
        orderService.create(order);
        return new CommonResult(200, "订单创建完成");
    }

    @GetMapping("/order/list")
    public CommonResult orderList(String userid) {
        Object o = orderService.list(userid);
        Object o2 = accountService.query(userid);
        Map map = new HashMap();
        map.put("order",o);
        map.put("acount",o2);
        return new CommonResult(200, "订单查询完成",map);
    }



}
