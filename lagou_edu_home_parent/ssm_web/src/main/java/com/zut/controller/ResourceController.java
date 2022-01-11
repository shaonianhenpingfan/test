package com.zut.controller;

import com.github.pagehelper.PageInfo;
import com.zut.domain.Resource;
import com.zut.domain.ResourceVo;
import com.zut.domain.ResponseResult;
import com.zut.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> allResource = resourceService.findAllResource(resourceVo);
        return new ResponseResult(true, 200, "查询全部资源成功", allResource);
    }
}
