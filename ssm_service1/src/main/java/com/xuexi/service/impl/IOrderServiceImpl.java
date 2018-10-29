package com.xuexi.service.impl;

import com.github.pagehelper.PageHelper;
import com.xuexi.dao.IOrdersDao;
import com.xuexi.domain.Orders;
import com.xuexi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IOrderServiceImpl implements IOrderService {

    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        // 参数pageNum是页码值,pageSize是每页显示条数;
        PageHelper.startPage(page, size);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return iOrdersDao.findById(ordersId);
    }
}
