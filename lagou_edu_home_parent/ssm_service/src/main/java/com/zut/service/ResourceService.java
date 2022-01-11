package com.zut.service;

import com.github.pagehelper.PageInfo;
import com.zut.domain.Resource;
import com.zut.domain.ResourceVo;

import java.util.List;

public interface ResourceService {

    /**
     * 查询全部资源信息
     * */
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);
}
