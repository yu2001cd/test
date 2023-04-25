package com.itheima.config;

import com.itheima.intercepter.LoginIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//配置类
public class Webconfig implements WebMvcConfigurer {
    @Autowired
    private LoginIntercepter loginIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//注册拦截器
        registry.addInterceptor(loginIntercepter).addPathPatterns("/**").excludePathPatterns("/login");// /*一级路径   /**任意级路径
    }
}
