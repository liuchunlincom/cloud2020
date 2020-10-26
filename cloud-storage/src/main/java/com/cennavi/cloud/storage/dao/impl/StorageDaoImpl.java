package com.cennavi.cloud.storage.dao.impl;

import com.cennavi.cloud.storage.dao.StorageDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Rain
 * @Date 2020/10/22 12:26
 **/
@Repository
public class StorageDaoImpl implements StorageDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public int decrease(Long productId, Integer count) {
        String sql = "update base_storage set left_num = left_num - " + count + " where id = '" + productId + "'";
        return jdbcTemplate.update(sql);
    }
}
