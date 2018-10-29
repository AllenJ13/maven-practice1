package com.xuexi.dao;


import com.xuexi.domain.Role;
import com.xuexi.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {


    @Select("select * from users where username = #{username}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.xuexi.dao.IRoleDao.findById"))
    })
    UserInfo findByUserName(String username) throws Exception;


    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;


    @Insert("insert into users (username,email,password,phoneNum,status) values (#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);


    @Select("select * from users where id = #{id}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.xuexi.dao.IRoleDao.findById"))
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in (select roleid from users_role where userid= #{userid})")
    List<Role> findByIdRoleNotAdd(String userId)throws Exception;

    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUsee(@Param("userId") String userId, @Param("roleId") String roleId);
}
