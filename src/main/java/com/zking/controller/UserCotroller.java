package com.zking.controller;

import com.zking.model.User;
import com.zking.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserCotroller {
    @Autowired
    private IUserService userService;

    @RequestMapping("/userList")
    @RequiresRoles("管理员")
    public String list(HttpSession session){
        List<User> users = userService.list();
        session.setAttribute("users",users);
        return "userList";
    }


    @RequestMapping("/delUser")
    public String delUser(Integer userid){
        int i = userService.deleteByPrimaryKey(userid);
        return "redirect:userList";
    }



}
