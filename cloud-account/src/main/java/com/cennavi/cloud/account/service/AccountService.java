package com.cennavi.cloud.account.service;

import java.math.BigDecimal;

public interface AccountService {

    void decrease(Long userId, BigDecimal money);

    Object query(Long userId);
}
