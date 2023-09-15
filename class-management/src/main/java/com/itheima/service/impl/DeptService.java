package com.itheima.service.impl;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {
    List<Dept> list();


    void delete(Integer id);

    void add(Dept dept);

    Dept findById(Integer id);

    void update(Dept dept);
}
