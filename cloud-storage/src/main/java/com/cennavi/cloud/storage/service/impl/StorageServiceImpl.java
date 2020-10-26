package com.cennavi.cloud.storage.service.impl;

import com.cennavi.cloud.storage.dao.StorageDao;
import com.cennavi.cloud.storage.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }

}
