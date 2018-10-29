package com.xuexi.controller;


import com.xuexi.domain.Role;
import com.xuexi.domain.UserInfo;
import com.xuexi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/findAll.do")
//    @PreAuthorize("hasRole(ROLE_ADMIN)")
    public ModelAndView findAll() throws Exception {
        List<UserInfo> userList = iUserService.findAll();
        for (UserInfo userInfo : userList) {
            System.out.println(userInfo);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    //用户添加
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo) {
        iUserService.save(userInfo);
        return "redirect:findAll.do";
    }

    //查询指定id 的用户

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = iUserService.findById(id);

        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("user-show1");
        return modelAndView;
    }


    //用户关联角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(value = "id", required = true) String userId) throws Exception {
        //1.根据userId查询出所有用户
        UserInfo user = iUserService.findById(userId);

        //2.查询该用户未添加的角色信息
        List<Role> roles = iUserService.findByIdRoleNotAdd(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("roleList", roles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }


    @RequestMapping("/addRoleToUser.do")
    public String AddRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] ids) throws Exception {
        iUserService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
