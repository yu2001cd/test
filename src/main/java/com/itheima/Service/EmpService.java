package com.itheima.Service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {

    public PageBean select(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp serlectbyId(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
