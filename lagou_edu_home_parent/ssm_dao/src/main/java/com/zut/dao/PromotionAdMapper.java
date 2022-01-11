package com.zut.dao;

import com.zut.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
     * 分页获取所有广告信息
     * */
    public List<PromotionAd> findAllAdByPage();

    /**
     * 新增广告
     * */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 更新广告
     * */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     * */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 修改广告状态信息
     * */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
