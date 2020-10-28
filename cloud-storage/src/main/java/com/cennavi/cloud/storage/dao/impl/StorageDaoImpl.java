package com.cennavi.cloud.storage.dao.impl;

import com.cennavi.cloud.storage.dao.StorageDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public String qeuryNameById(String productId) {

        String sql = "select name from base_storage where id ='" + productId + "'";
        try {
            return jdbcTemplate.queryForObject(sql,String.class);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String,Object>> queryGeom(String wkt) {

        String sql = "select id,st_astext(geom) as geom from rqingdao where st_intersects(geom,st_geomfromtext('"+ wkt+"',4326))";
        try {
            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
            return list;
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }
}
