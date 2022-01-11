package com.zut.controller;

import com.zut.domain.Menu;
import com.zut.domain.ResponseResult;
import com.zut.domain.Role;
import com.zut.domain.RoleMenuVo;
import com.zut.service.MenuService;
import com.zut.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllUserByPage(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "查询角色成功", allRole);
    }

    /**
     * -1是查询所有信息
     * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        return new ResponseResult(true, 200, "查询所有信息成功", menuList);
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<String> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "根据角色id查询关联菜单成功", menuByRoleId);
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
        return new ResponseResult(true, 200, "用户与订单关联成功", null);
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true, 200, "删除角色成功", null);
    }
}
