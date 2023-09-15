package com.itheima.service.impl;


import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void add(Emp emp);

    Emp findById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
