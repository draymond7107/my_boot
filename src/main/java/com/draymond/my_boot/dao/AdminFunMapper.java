package com.draymond.my_boot.dao;

import com.draymond.my_boot.entity.AdminFun;
import com.draymond.my_boot.entity.AdminFunExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AdminFunMapper {
    int countByExample(AdminFunExample example);

    int deleteByExample(AdminFunExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminFun record);

    int insertSelective(AdminFun record);

    List<AdminFun> selectByExample(AdminFunExample example);

    AdminFun selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminFun record, @Param("example") AdminFunExample example);

    int updateByExample(@Param("record") AdminFun record, @Param("example") AdminFunExample example);

    int updateByPrimaryKeySelective(AdminFun record);

    int updateByPrimaryKey(AdminFun record);
}