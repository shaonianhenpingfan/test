package com.zut.controller;

import com.zut.domain.Test;
import com.zut.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/findAllTest")
    public List<Test> findAllTest(){
        return testService.findAllTest();
    }

}
