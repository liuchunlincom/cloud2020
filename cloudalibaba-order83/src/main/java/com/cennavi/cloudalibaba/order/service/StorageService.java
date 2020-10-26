package com.cennavi.cloudalibaba.order.service;

import com.cennavi.cloudalibaba.commons.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-storage")
public interface StorageService {

    @PostMapping("storage/decrease")
    CommonResult decrease(@RequestParam("productId") String productId,
                          @RequestParam("count") Integer count);

}
