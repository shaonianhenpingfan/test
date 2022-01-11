package com.zut.controller;

import com.github.pagehelper.PageInfo;
import com.zut.domain.PromotionAd;
import com.zut.domain.PromotionAdVo;
import com.zut.domain.ResponseResult;
import com.zut.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAd")
    public ResponseResult findAllAdByPage(PromotionAdVo adVo) {
        PageInfo allAdByPage = promotionAdService.findAllAdByPage(adVo);
        return new ResponseResult(true, 200, "分页查询成功", allAdByPage);
    }

    @RequestMapping("saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "添加广告成功", null);
        }else{
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "更新广告成功", null);
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id){
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "根据id查询广告成功", promotionAdById);
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam int id, @RequestParam int status){
        if (status == 1){
            promotionAdService.updatePromotionAdStatus(id, status);
        }else {
            promotionAdService.updatePromotionAdStatus(id, 0);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "状态修改成功", map);
    }

    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        String realPath = request.getServletContext().getRealPath("/");
        String webappPath = realPath.substring(0, realPath.indexOf("ssm_web"));

        String originalFilename = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis() + originalFilename.substring(1, originalFilename.lastIndexOf("."));
        String uploadPath = webappPath + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        file.transferTo(filePath);

        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8888/upload" + newFileName);
        return new ResponseResult(true, 200, "图片上传成功", map);
    }
}
