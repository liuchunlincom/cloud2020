package com.cennavi.cloudalibaba.payment.service.impl;

import com.cennavi.cloudalibaba.payment.dao.ITaskDao;
import com.cennavi.cloudalibaba.payment.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Rain
 * @Date 2020/10/15 15:27
 **/
@Slf4j
@Service
public class TaskServiceImpl implements ITaskService {

    @Resource
    ITaskDao taskDao;

    @Override
    public Map getTask() {
        Map map = taskDao.getTask();
        return map;
    }
}
