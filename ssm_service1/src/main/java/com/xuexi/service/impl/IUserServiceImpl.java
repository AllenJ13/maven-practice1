package com.xuexi.service.impl;

import com.xuexi.dao.IUserDao;
import com.xuexi.domain.Role;

import com.xuexi.domain.UserInfo;
import com.xuexi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        //  userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }


    /**
     * 添加角色关联到用户
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findByIdRoleNotAdd(String userId) throws Exception {
        return userDao.findByIdRoleNotAdd(userId);
    }

    /**
     * 添加用户关联到角色
     *
     * @param userId
     * @param ids
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String roleId : ids) {
            userDao.addRoleToUsee(userId, roleId);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }


    //获取权限用户
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
