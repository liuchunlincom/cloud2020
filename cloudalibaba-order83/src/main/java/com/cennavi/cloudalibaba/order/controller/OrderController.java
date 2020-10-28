package com.cennavi.cloudalibaba.order.controller;

import com.cennavi.cloudalibaba.commons.CommonResult;
import com.cennavi.cloudalibaba.order.beans.Storage2;
import com.cennavi.cloudalibaba.order.model.Order;
import com.cennavi.cloudalibaba.order.service.AccountService;
import com.cennavi.cloudalibaba.order.service.OrderService;
import com.cennavi.cloudalibaba.order.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;


    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        order.setId(UUID.randomUUID().toString().replaceAll("-",""));
        orderService.create(order);
        return new CommonResult(200, "订单创建完成");
    }

    @GetMapping("/order/list")
    public CommonResult orderList(String userid) {
        Object o = orderService.list(userid);
        return new CommonResult(200, "订单查询完成",o);
    }

    @GetMapping("/order/details")
    public CommonResult details(String orderid) {
        Map<String,Object> orderMap = orderService.queryByid(orderid);
        Storage2 storage = storageService.qeuryNameById(orderMap.get("productid").toString());
        orderMap.put("prodctname",storage.getName());
        return new CommonResult(200, "订单查询完成",orderMap);

    }

    /**
     * 测试geometry对象结果调用
     * 测试结果：不可行，需要转GEOJSON或者wkt
     * @return
     */
    @GetMapping("/order/testGeom")
    public CommonResult details() {
        Geometry geometry = storageService.testGeom();
        log.info(geometry.toText());
        return new CommonResult(200, "订单查询完成", geometry.toText());

    }

    /**
     * @return
     */
    @GetMapping(value = "order/testPbf/{z}/{x}/{y}")
    public byte[] testPbf(@PathVariable int x,
                          @PathVariable int y,
                          @PathVariable int z) {
        log.info("pbf测试。。。。" + x + "," + y +"," + z);
        return storageService.testPbf(z,x,y);

    }

    @GetMapping(value = "order/testPic")
    public ResponseEntity<byte[]> testPic(){
        return storageService.testPic();
    }



}
