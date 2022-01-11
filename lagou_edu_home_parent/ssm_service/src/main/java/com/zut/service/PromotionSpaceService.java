package com.zut.service;

import com.zut.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    /**
     * 查询所有广告位
     * */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 添加广告位
     * */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改公告位
     * */
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id 查询广告位信息
     * */
    PromotionSpace findPromotionSpaceById(int id);
}
