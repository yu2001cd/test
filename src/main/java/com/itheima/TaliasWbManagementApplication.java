package com.itheima;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//开启对servlet组件的支持
@SpringBootApplication
public class TaliasWbManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaliasWbManagementApplication.class, args);}

}
