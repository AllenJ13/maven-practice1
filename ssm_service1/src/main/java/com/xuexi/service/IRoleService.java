package com.xuexi.service;

import com.xuexi.domain.Permission;
import com.xuexi.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermission(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] ids) throws Exception;
}
