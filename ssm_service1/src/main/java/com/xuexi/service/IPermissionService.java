package com.xuexi.service;

import com.xuexi.domain.Permission;

import java.util.List;

public interface IPermissionService {


    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
