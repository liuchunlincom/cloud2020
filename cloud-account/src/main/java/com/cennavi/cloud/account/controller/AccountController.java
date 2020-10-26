package com.cennavi.cloud.account.controller;

import com.cennavi.cloud.account.service.AccountService;
import com.cennavi.cloudalibaba.commons.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,
                                 @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功");
    }

    @GetMapping("account/query")
    public CommonResult query(@RequestParam("userId") Long userId) {
        Object o = accountService.query(userId);
        return new CommonResult(200, "",o);
    }


}
