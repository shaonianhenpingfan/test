package com.zut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zut.dao.UserMapper;
import com.zut.domain.*;
import com.zut.service.UserService;
import com.zut.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);

        System.out.println("总条数："+userPageInfo.getTotal());
        System.out.println("总页数："+userPageInfo.getPages());
        System.out.println("当前页："+userPageInfo.getPageNum());
        System.out.println("每页显示长度："+userPageInfo.getPageSize());
        System.out.println("是否第一页："+userPageInfo.isIsFirstPage());
        System.out.println("是否最后一页："+userPageInfo.isIsLastPage());

        return userPageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id, status);
    }

    @Override
    public User login(User user) {
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(), "lagou", user1.getPassword())){
            return user1;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        return userMapper.findUserRelationRoleById(id);
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());

        for (Integer roleId : userVo.getRoleIdList()){
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            user_role_relation.setCreatedTime(new Date());
            user_role_relation.setUpdatedTime(new Date());
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        //1.获取当前用户所拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        //2.获取角色id，保存到list中
        List<Integer> list = new ArrayList<>();

        for (Role role : roleList) {
            list.add(role.getId());
        }

        //3.根据角色查询父菜单
        List<Menu> menuList = userMapper.findParentMenuByRoleId(list);

        //4.封装父菜单下的子菜单
        for (Menu menu : menuList) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        //5.获取资料权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        Map<String, Object> map = new HashMap<>();
        map.put("menuList", menuList);
        map.put("resourceList", resourceList);

        return new ResponseResult(true, 200, "响应成功", map);
    }
}
