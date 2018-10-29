package com.xuexi.service.impl;

import com.xuexi.dao.IPermissionDao;
import com.xuexi.domain.Permission;
import com.xuexi.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return iPermissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        iPermissionDao.save(permission);
    }
}
