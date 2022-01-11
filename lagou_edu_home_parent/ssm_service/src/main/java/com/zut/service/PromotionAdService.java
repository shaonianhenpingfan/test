package com.zut.service;

import com.github.pagehelper.PageInfo;
import com.zut.domain.PromotionAd;
import com.zut.domain.PromotionAdVo;


public interface PromotionAdService {

    /**
     * 分页获取所有广告信息
     * */
    public PageInfo findAllAdByPage(PromotionAdVo promotionAdVo);

    /**
     * 新增广告
     * */
    void savePromotionAd(PromotionAd promotionAd);

    /**
     * 更新广告
     * */
    void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     * */
    PromotionAd findPromotionAdById(int id);

    /**
     * 设置广告状态信息
     * */
    void updatePromotionAdStatus(int id, int status);
}
