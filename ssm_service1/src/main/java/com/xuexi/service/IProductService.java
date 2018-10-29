package com.xuexi.service;

import com.xuexi.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Product> findAll() throws Exception;


    /**
     * 保存
     */
    void save(Product product) throws Exception;
}
