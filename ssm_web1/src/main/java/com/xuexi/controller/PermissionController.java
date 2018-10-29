package com.xuexi.controller;

import com.xuexi.domain.Permission;
import com.xuexi.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Permission> permissionList = iPermissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }


    @RequestMapping("save.do")
    public String save(Permission permission) throws Exception {
        iPermissionService.save(permission);
        return "redirect:findAll.do";
    }
}
