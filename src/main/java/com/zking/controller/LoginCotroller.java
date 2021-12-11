package com.zking.controller;

import com.zking.model.User;
import com.zking.service.IUserService;
import com.zking.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.rmi.activation.UnknownObjectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginCotroller {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        String msg="";
        int code=1;
        Map<String,Object> map=new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            msg="账号不存在";
            code=-1;
        } catch (IncorrectCredentialsException e1){
            msg="密码错误";
            code=0;
        }
        map.put("code",code);
        map.put("msg",msg);
        session.setAttribute("map",map);
        if(code<1){
            return "login";
        }else {
            return "main";
        }
    }

    @RequestMapping("/gologin")
    public String gologin(){
        return "login";
    }


    @RequestMapping("/zhuze")
    public String zhuce(){
        return "zhuce";
    }

    @RequestMapping("/gozhuce")
    public String gozhuce(User user,HttpSession session){
        String msg="新增成功";
        Map<String,Object> map=new HashMap<>();
       if(user!=null){
           String salt= PasswordHelper.createSalt();
           user.setSalt(salt);
           String credentials = PasswordHelper.createCredentials(user.getPassword(), salt);
           user.setPassword(credentials);
           int i = userService.insert(user);
           if(i<0){
               msg="新增失败";
           }
           map.put("msg",msg);
           session.setAttribute("map",map);
       }
        return "redirect:gologin";
    }

}
