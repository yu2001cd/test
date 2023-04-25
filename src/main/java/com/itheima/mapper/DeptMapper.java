package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
@Mapper
public interface DeptMapper {
    /*查询全部部门*/
    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    @Insert("insert into dept (name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);
    @Select("select * from dept where id=#{id}")
    Dept select(Integer id);
    @Update("update dept set name=#{name},update_time=#{now} where id=#{id}")
    void edit(Integer id, String name, LocalDateTime now);
}
