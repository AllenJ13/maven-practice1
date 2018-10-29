package com.xuexi.service.impl;

import com.xuexi.dao.IRoleDao;
import com.xuexi.domain.Permission;
import com.xuexi.domain.Role;
import com.xuexi.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() throws Exception {

        return iRoleDao.findAll();
    }


    @Override
    public void save(Role role) throws Exception {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return iRoleDao.findByRoleId(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception {
        return iRoleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String id : ids) {
            iRoleDao.addPermissionToRole(roleId, id);
        }
    }
}
