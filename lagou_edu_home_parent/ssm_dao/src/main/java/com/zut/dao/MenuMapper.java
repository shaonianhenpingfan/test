package com.zut.dao;

import com.zut.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /**
     * 按id查询全部父子菜单信息
     * */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     * */
    public List<Menu> findAllMenu();

    /**
     *根据id查询订单信息
     * */
    public Menu findMenuById(int id);
}
