package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
@Mapper
public interface EmpMapper {
    /*@Select("select count(*) from emp")
    public long count();

    @Select("select * from emp limit #{start},#{end}")
    public List<Emp> page(Integer start,Integer end);*/


    /*员工信息查询*/
    /*@Select("select * from emp")*/
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
    void delete(List<Integer> ids);

    @Insert("insert into emp(username,name,gender,image,job,entrydate,create_time,update_time) values" +
            " (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{createTime},#{updateTime});")
    void insert(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp selectbyID(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);
    @Delete("DELETE from emp where dept_id=#{i}")
    void deletebydeptid (Integer i);
}
