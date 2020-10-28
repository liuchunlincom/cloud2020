package com.cennavi.cloudalibaba.order.dao.impl;

import com.cennavi.cloudalibaba.order.dao.OrderDao;
import com.cennavi.cloudalibaba.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @Date 2020/10/22 13:43
 **/
@Repository
@Slf4j
public class OrderDaoImpl implements OrderDao {
    @Resource
    JdbcTemplate jdbcTemplate;


    @Override
    public void create(Order order) {

        String sql = "insert into base_order values(?,?,?,?,?,?)";

        jdbcTemplate.update(sql,new Object[]{order.getId(),order.getUserid(),order.getProductid(),order.getCount(),order.getMoney(),'0'});

    }

    @Override
    public void update(String orderid, Integer status) {
        String sql = "update base_order set status = ? where id = ?";
        jdbcTemplate.update(sql,new Object[]{
                status,orderid
        });

    }

    @Override
    public List<Map<String,Object>> list(String userid) {
        String sql = "select * from base_order  where userid = '" + userid + "'";
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, Object> queryById(String orderid) {

        String sql = "select * from base_order  where id = '" + orderid + "'";
        try {

            return jdbcTemplate.queryForMap(sql);

        } catch (DataAccessException e) {
            return new HashMap<>();
        }
    }
}
