package com.cennavi.cloud.account.dao;


import java.math.BigDecimal;

public interface AccountDao {
    int decrease(Long userId,BigDecimal money);

    Object query(Long userId);
}
