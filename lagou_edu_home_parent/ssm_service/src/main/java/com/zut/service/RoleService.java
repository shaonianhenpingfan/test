package com.zut.service;

import com.zut.domain.Role;
import com.zut.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /**
     * 按条件查询角色信息
     * */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色信息查询关联菜单
     * */
    public List<String> findMenuByRoleId(Integer roleId);

    /**
     * 角色和菜单信息关联
     * */
    void RoleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     * */
    void deleteRole(Integer id);
}
