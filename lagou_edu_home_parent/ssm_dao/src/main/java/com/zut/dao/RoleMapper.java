package com.zut.dao;

import com.zut.domain.Role;
import com.zut.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /**
     * 按条件查询角色信息
     * */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询角色信息
     * */
    public List<String> findMenuByRoleId(Integer roleId);

    /**
     * 删除角色和菜单的关联信息
     * */
    public void deleteRoleContextMenu(int id);

    /**
     * 角色菜单相关联
     * */
    public void RoleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色
     * */
    void deleteRole(Integer id);


}
