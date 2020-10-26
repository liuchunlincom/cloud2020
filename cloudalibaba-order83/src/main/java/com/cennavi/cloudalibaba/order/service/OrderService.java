package com.cennavi.cloudalibaba.order.service;

import com.cennavi.cloudalibaba.order.model.Order;

public interface OrderService {

    void create(Order order);

    Object list(String userid);


}
