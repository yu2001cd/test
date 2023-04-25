package com.itheima.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpMapper empMapper;
   /* @Override
    public PageBean select(Integer page, Integer pageSize) {
        long count = empMapper.count();
        List<Emp> emps = empMapper.page((page - 1) * pageSize, pageSize);
        PageBean pageBean = new PageBean(count,emps);
        return pageBean;
    }*/

    @Override
    public PageBean select(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        /*设置分页参数*/
        PageHelper.startPage(page,pageSize);
        /*查询*/
        List<Emp> emplist = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) emplist;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp serlectbyId(Integer id) {
        Emp emp = empMapper.selectbyID(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp emp2 = empMapper.login(emp);
        return emp2;
    }
}
