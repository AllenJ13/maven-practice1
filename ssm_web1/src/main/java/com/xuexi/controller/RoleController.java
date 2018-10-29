package com.xuexi.controller;

import com.xuexi.domain.Permission;
import com.xuexi.domain.Role;
import com.xuexi.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Role> roleList = iRoleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }


    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        iRoleService.save(role);
        return "redirect:findAll.do";
    }

    //根据roleid查询角色,并查询出可以添加的权限信息
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        //根据roleId查询role
        Role role = iRoleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> permissionList = iRoleService.findOtherPermission(roleId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    //给角色添加权限
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] ids) throws Exception {
        iRoleService.addPermissionToRole(roleId, ids);
        return "redirect:findAll.do";
    }
}
