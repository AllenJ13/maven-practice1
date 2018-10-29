package com.xuexi.dao;

import com.xuexi.domain.Permission;
import com.xuexi.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {


    @Select("select * from role where id in ( select roleid from users_role where userid= #{id} )")
    @Results(value = {
            @Result(id = true, property = "id", column = "ID"),
            @Result(property = "roleName", column = "ROLENAME"),
            @Result(property = "roleDesc", column = "ROLEDESC"),
            @Result(property = "permissions", column = "ID", javaType = java.util.List.class, many = @Many(select = "com.xuexi.dao.IPermissionDao.findByID")),
    })
    List<Role> findById(String id);


    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{roleId}")
    Role findByRoleId(String roleId) throws Exception;


    @Select("select * from permission where id not in (select permissionId from role_permission where roleId= #{roleId})")
    List<Permission> findOtherPermission(String roleId) throws Exception;

    @Insert("insert into role_permission (roleId,permissionId)values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String id) throws Exception;
}
