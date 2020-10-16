package com.cennavi.cloudalibaba.payment.dao.impl;

import com.cennavi.cloudalibaba.payment.dao.ITaskDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Rain
 * @Date 2020/10/15 15:23
 **/
@Slf4j
@Repository
public class TaskDaoImpl implements ITaskDao {
    @Resource
    JdbcTemplate jdbcTemplate;
    @Override
    public Map getTask() {

        String sql = " select * from task_info limit 1";
        Map<String,Object> map = jdbcTemplate.queryForMap(sql);
        return map;
    }
}
