package com.zking.service;

import com.zking.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface IUserService {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据账号查到个人信息
     */
    User selectByUserName(String username);
    /**
     * 根据用户名查到用户角色
     * @param username
     * @return
     */
    public Set<String> getRoleByUsername(String username);

    /**
     * 根据用户名查询到用户权限
     * @param username
     * @return
     */
    public Set<String> getPermissionByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    public List<User> list();



}