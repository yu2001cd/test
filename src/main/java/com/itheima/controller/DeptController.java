package com.itheima.controller;

import com.itheima.Service.DeptService;
import com.itheima.Service.EmpService;
import com.itheima.annotation.LogAnnotation;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j//日志
@RestController
@RequestMapping("/depts")//抽取公共路径
public class DeptController {
    @Autowired
    private DeptService deptService;
    //查询方法
    @GetMapping/*限定请求方式为get*/
    public Result list (){
        List<Dept> depts = deptService.list();
        log.info("查询全部部门数据");
        return Result.success(depts);
    }

    //删除方法
    @DeleteMapping("/{id}" )
    @LogAnnotation
    public Result delete(@PathVariable Integer id){//获取参数
        deptService.delete(id);
        log.info("删除部门"+id);
        return Result.success();
    }

    //添加方法
    @PostMapping
    @LogAnnotation
    public Result add(@RequestBody Dept dept){//将post请求携带的数据封装到对象中
        deptService.add(dept);
        log.info("添加部门");
        return Result.success();
    }


    //根据id查询
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        Dept dept = deptService.select(id);
        log.info("查询部门"+id);
        return Result.success(dept);
    }

    //修改信息
    @PutMapping
    @LogAnnotation
    public Result edit(@RequestBody Dept dept){
        Dept dept2 = deptService.edit(dept);
        log.info("修改部门"+dept.getId()+"为"+dept.getName());
        return Result.success(dept2);
    }
}
