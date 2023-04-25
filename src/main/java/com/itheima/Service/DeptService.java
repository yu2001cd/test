package com.itheima.Service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

     void add(Dept dept);

    Dept select(Integer id);

    Dept edit(Dept dept);
}
