package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*查询全部部门信息*/
    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept findById(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
