package com.zut.controller;

import com.github.pagehelper.PageInfo;
import com.zut.domain.ResponseResult;
import com.zut.domain.Role;
import com.zut.domain.User;
import com.zut.domain.UserVo;
import com.zut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);
        List<User> list = allUserByPage.getList();
        System.out.println(list);

        return new ResponseResult(true, 200, "查询所有用户成功", allUserByPage);
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id , @RequestParam String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else{
            status = "ENABLE";
        }
        userService.updateUserStatus(id, status);
        return new ResponseResult(true, 200, "修改用户状态成功", status);
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request){
        User login = userService.login(user);

        if (login != null){
            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", login.getId());
            HttpSession session = request.getSession();
            session.setAttribute("user_id", login.getId());
            session.setAttribute("access_token", access_token);
            return new ResponseResult(true, 200, "登陆成功", map);
        }else{
            return new ResponseResult(true, 200, "用户名或密码错误", null);
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "查询当前角色成功", roleList);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //1.获取请求头中的token
        String token = request.getHeader("Authorization");

        //2.获取session中的token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        //3.判断
        if (token.equals(access_token)){
            Integer user_id = (Integer) session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }
        return new ResponseResult(false, 404, "资源获取失败", null);
    }
}
