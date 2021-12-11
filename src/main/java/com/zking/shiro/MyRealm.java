package com.zking.shiro;

import com.zking.model.User;
import com.zking.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    //权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        Set<String> roles = userService.getRoleByUsername(username);
        Set<String> permissions = userService.getPermissionByUsername(username);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //设置用户角色信息
        info.setRoles(roles);
        //设置用户权限
        info.setStringPermissions(permissions);
        return info;
    }

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取前端用户输入的用户名
        String username =authenticationToken.getPrincipal().toString();
        //根据用户名得到用户的信息
        User user = userService.selectByUserName(username);
        if(user!=null){
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
            System.out.println("890"+info);
            return info;
        }else {
            return null;
        }


    }
}
