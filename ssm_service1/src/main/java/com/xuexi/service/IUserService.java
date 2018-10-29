package com.xuexi.service;


import com.xuexi.domain.Role;
import com.xuexi.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;


    void save(UserInfo userInfo);

    UserInfo findById(String id) throws Exception;

    //给用户关联角色信息
    List<Role> findByIdRoleNotAdd(String userId) throws Exception;


    void addRoleToUser(String userId, String[] ids) throws Exception;
}
