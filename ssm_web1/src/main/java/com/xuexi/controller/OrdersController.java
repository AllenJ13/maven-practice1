package com.xuexi.controller;

import com.github.pagehelper.PageInfo;
import com.xuexi.domain.Orders;
import com.xuexi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//查询所有订单
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrderService iOrderService;

//    //    查询全部订单---未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        List<Orders> ordersList = iOrderService.findAll();
//        modelAndView.addObject("ordersList", ordersList);
//        modelAndView.setViewName("orders-list");
//
//        return modelAndView;
//    }

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "4") int size) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> ordersList = iOrderService.findAll(page, size);
        // pageInfo就是一个分页的bean
        PageInfo pageInfo = new PageInfo(ordersList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }


    /**
     * 查询订单详情
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        Orders orders = iOrderService.findById(ordersId);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
