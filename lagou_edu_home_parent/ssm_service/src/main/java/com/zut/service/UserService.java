package com.zut.service;

import com.github.pagehelper.PageInfo;
import com.zut.domain.ResponseResult;
import com.zut.domain.Role;
import com.zut.domain.User;
import com.zut.domain.UserVo;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户
     * */
    public PageInfo findAllUserByPage(UserVo userVo);

    /**
     * 修改用户状态信息
     * */
    public void updateUserStatus(int id, String status);

    /**
     * 用户登陆
     * */
    public User login(User user);

    /**
     * 获取用户拥有的角色
     * */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 用户关联角色
     * */
    public void userContextRole(UserVo userVo);

    /*
     * 获取用户权限
     * */
    public ResponseResult getUserPermissions(Integer id);
}
