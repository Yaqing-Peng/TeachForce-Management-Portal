package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.impl.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;


    @GetMapping
    public Result page(@RequestParam(defaultValue  = "1") Integer page,
                       @RequestParam(defaultValue  = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询, 参数: {},{},{},{},{},{}" , page,pageSize,name,gender,begin,end);
        //调用service查询员工数据
        PageBean pageBean =  empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delecte(@PathVariable List<Integer> ids){
        log.info("批量删除员工 ids:{}",ids);
        //调用service删除员工
        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("新增员工: {}" , emp);
        //调用service新增员工
        empService.add(emp);
        return Result.success();
    }

    //根据id查询员工
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询员工 id:{}",id);
        //调用service查询员工
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    @Log
    //根据id更新员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息: {}" , emp);
        //调用service更新员工信息
        empService.update(emp);
        return Result.success();
    }

}
