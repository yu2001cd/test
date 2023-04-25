package com.itheima.controller;

import com.itheima.Service.EmpService;
import com.itheima.Utils.JwtUtils;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp emp2 = empService.login(emp);
        log.info("执行登录操作{}",emp);
        if (emp2!=null){//登录成功下发jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",emp2.getId());
            claims.put("name",emp2.getName());
            claims.put("username",emp2.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("用户信息错误");
    }
}
