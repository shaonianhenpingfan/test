package com.zut.controller;

import com.zut.domain.Menu;
import com.zut.domain.ResponseResult;
import com.zut.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = menuService.findAllMenu();
        return new ResponseResult(true, 200, "查询菜单成功", list);
    }

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id){
        if(id == -1){
            //添加操作，回显不需要操作的信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", menuList);
            return new ResponseResult(true, 200, "查询成功", map);
        }else{
            //修改操作，回显全部信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            Map<String, Object> map = new HashMap<>();
            map.put("menu", menu);
            map.put("parentMenuList", menuList);
            return new ResponseResult(true, 200, "查询成功", map);
        }
    }
}
