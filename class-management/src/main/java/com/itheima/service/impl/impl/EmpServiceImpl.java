package com.itheima.service.impl.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.impl.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    public PageBean page(Integer page, Integer pageSize) {
//        //获取总记录数
//        Long count = empMapper.count();
//        //计算起始索引
//        Integer start= (page-1)*pageSize;
//        //获取当前页的结果列表
//        List<Emp> empList = empMapper.page(start,pageSize);
//        //封装PageBean对象
//        PageBean pageBean = new PageBean(count,empList);
//        return pageBean;
//    }

    //    pagehelper 简化版
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行查询, 强转为Page对象
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;

        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    public Emp findById(Integer id){
        return empMapper.findById(id);
    }

    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
