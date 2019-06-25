package com.xuexi.dao;

import com.xuexi.domain.Member;
import org.apache.ibatis.annotations.Select;

//dao层
public interface IMemberDao {

    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId);
}
