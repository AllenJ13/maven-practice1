package com.xuexi.dao;

import com.xuexi.domain.Member;
import com.xuexi.domain.Orders;
import com.xuexi.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {


    @Select("select * from orders")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.xuexi.dao.IProductDao.findById")),
    })
    List<Orders> findAll() throws Exception;


    /**
     * 查询订单详情
     *
     * @param
     * @return
     */
    @Select("select * from orders where id=#{ordersId}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType =Product.class,one = @One(select = "com.xuexi.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.xuexi.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select ="com.xuexi.dao.ITravellsDao.findById" )),
    })
    Orders findById(String ordersId)throws Exception;
}
