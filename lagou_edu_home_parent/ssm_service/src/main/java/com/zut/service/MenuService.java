package com.zut.service;

import com.zut.domain.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 按id查询父子菜单所有信息
     * */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     * */
    public List<Menu> findAllMenu();

    public Menu findMenuById(int id);
}
