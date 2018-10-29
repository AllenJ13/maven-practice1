package com.xuexi.dao;

import com.xuexi.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellsDao {


    @Select("select * from traveller where id in (select travellerid from order_traveller where orderid=#{id})")
    List<Traveller> findById(String id)throws Exception;
}
