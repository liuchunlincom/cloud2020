package com.cennavi.cloudalibaba.order.dao;


import com.cennavi.cloudalibaba.order.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    //1 新建订单
    void create(Order order);

    //2 修改订单状态
    void update(String orderid, Integer status);

    //3 查询订单
    List<Map<String,Object>> list(String userid);

    //根据订单编号查询订单
    Map<String,Object> queryById(String orderid);
}
