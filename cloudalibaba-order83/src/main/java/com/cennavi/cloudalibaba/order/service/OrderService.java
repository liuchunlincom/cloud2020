package com.cennavi.cloudalibaba.order.service;

import com.cennavi.cloudalibaba.order.model.Order;

import java.util.Map;

public interface OrderService {

    void create(Order order);

    Object list(String userid);


    Map<String,Object> queryByid(String orderid);
}
