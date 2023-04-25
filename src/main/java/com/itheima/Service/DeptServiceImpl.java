package com.itheima.Service;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> list() {
        List<Dept> depts = deptMapper.list();
        return depts;
    }

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
        empMapper.deletebydeptid(id);
    }


    @Transactional(rollbackFor = Exception.class)//开启一个事务  默认runtimeException才回滚 通过rollbackfor控制出现什么类型异常回滚
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept select(Integer id) {
        Dept dept = deptMapper.select(id);
       return dept;
    }

    @Override
    public Dept edit( Dept dept) {
        Dept dept2= deptMapper.select(dept.getId());
        deptMapper.edit(dept.getId(),dept.getName(),LocalDateTime.now());
        dept2.setName(dept.getName());
        dept2.setUpdateTime(LocalDateTime.now());
        return dept2;
    }

}
