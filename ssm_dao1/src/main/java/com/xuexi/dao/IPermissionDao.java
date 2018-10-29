package com.xuexi.dao;


import com.xuexi.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select PERMISSIONID from ROLE_PERMISSION where ROLEID= #{id}) ")
    List<Permission> findByID(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
