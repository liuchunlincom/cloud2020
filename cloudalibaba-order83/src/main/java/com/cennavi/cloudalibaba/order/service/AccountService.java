package com.cennavi.cloudalibaba.order.service;

import com.cennavi.cloudalibaba.commons.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "cloud-account")
public interface AccountService {
    @PostMapping(value = "account/decrease")
    CommonResult decrease(@RequestParam("userId") String userId,
                          @RequestParam("money") BigDecimal money);

    @GetMapping(value = "account/query")
    Object query(@RequestParam("userId") String userId);

}
