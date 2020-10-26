package com.cennavi.cloud.account.dao.impl;

import com.cennavi.cloud.account.dao.AccountDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Rain
 * @Date 2020/10/22 11:03
 **/
@Repository
public class AccountDaoImpl implements AccountDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public int decrease(Long userId, BigDecimal money) {
        String sql = "update base_account set account = account - " + money +" where userid = '" + userId + "'" ;
        return jdbcTemplate.update(sql);
    }

    @Override
    public Object query(Long userId) {
        String sql = "select * from base_account where userid = '" + userId + "' limit 1" ;
        try {
            return jdbcTemplate.queryForMap(sql);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
