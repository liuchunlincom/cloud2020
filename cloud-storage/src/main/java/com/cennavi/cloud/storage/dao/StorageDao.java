package com.cennavi.cloud.storage.dao;


import java.util.List;
import java.util.Map;

public interface StorageDao {
    int decrease(Long productId,Integer count);

    String qeuryNameById(String productId);

    public List<Map<String,Object>> queryGeom(String wkt);
}
