package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.impl.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    /*查询部门数据*/
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");//日志
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
    /*删除部门数据*/
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门数据: {}",id);
        deptService.delete(id);
        return Result.success();
    }

   /*新增部门*/
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /*根据id查询部门*/
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询部门: {}",id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /*修改部门数据*/
    @Log
    @PutMapping
    public Result update( @RequestBody Dept dept){
        log.info("根据id修改部门数据: {}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
