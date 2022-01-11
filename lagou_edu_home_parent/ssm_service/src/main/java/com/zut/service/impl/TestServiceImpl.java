package com.zut.service.impl;

import com.zut.dao.TestDao;
import com.zut.domain.Test;
import com.zut.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    @Override
    public List<Test> findAllTest() {
        return testDao.findAllTest();
    }
}
