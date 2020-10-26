package com.cennavi.cloudalibaba.order.service.impl;

import com.cennavi.cloudalibaba.order.dao.OrderDao;
import com.cennavi.cloudalibaba.order.model.Order;
import com.cennavi.cloudalibaba.order.service.AccountService;
import com.cennavi.cloudalibaba.order.service.OrderService;
import com.cennavi.cloudalibaba.order.service.StorageService;
import feign.FeignException;
//import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    //@GlobalTransactional(name = "create-order",rollbackFor = FeignException.class)
    public void create(Order order) {
        log.info("--------->开始新建订单");
        //1 新建订单
        orderDao.create(order);
        //2 扣减库存
        log.info("------------->订单微服务开始调用库存,做扣减Count");
        storageService.decrease(order.getProductid(), order.getCount());
        log.info("------------->订单微服务开始调用库存,做扣减end");
        //3 扣减账户
        log.info("------------->订单微服务开始调用账户,做扣减Money");
        accountService.decrease(order.getUserid(), order.getMoney());
        log.info("------------->订单微服务开始调用账户,做扣减end");

        //4 修改订单状态
        log.info("------------->修改订单状态开始");
        orderDao.update(order.getId(),1);
        log.info("------------->修改订单状态结束");

        log.info("------------->下订单结束了");
    }

    @Override
    public Object list(String userid) {
         return orderDao.list(userid);

    }
}
