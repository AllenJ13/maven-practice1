package com.xuexi.service.impl;

import com.xuexi.dao.IProductDao;
import com.xuexi.domain.Product;
import com.xuexi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();
    }


    @Override
    public void save(Product product) throws Exception {
        iProductDao.save(product);
    }
}
