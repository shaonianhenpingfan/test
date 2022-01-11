package com.zut.dao;

import com.zut.domain.Resource;
import com.zut.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    /**
     * 查询全部资源信息
     * */
    public List<Resource> findAllResource(ResourceVo resourceVo);

}
