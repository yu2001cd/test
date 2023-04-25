package com.itheima.controller;

import com.itheima.Service.EmpService;
import com.itheima.annotation.LogAnnotation;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    /*分页查询*/
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         String name,
                         Short gender,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询{},{},{},{},{}",page,pageSize,gender,begin,end);
        PageBean pageBean = empService.select(page, pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    /*批量删除*/
    @DeleteMapping("/{ids}")
    @LogAnnotation
    public Result delete(@PathVariable List<Integer> ids){
        empService.delete(ids);
        log.info("批量删除数据");
        return Result.success();
    }

    //新增员工
    @PostMapping
    @LogAnnotation
    public Result insert(@RequestBody Emp emp){
        empService.insert(emp);
        log.info("新增员工{}",emp);
        return Result.success();
    }

    //根据id查询员工
    @GetMapping("/{id}")
    public Result selectbyId (@PathVariable Integer id){
        Emp emp = empService.serlectbyId(id);
        log.info("查询员工{}",id);
        return Result.success(emp);
    }

    //修改员工
    @PutMapping
    @LogAnnotation
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        log.info("修改数据为{}",emp);
        return Result.success();
    }
}
