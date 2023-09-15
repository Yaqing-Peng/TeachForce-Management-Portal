package com.itheima.service.impl.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.impl.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
    //对所有的异常进行回滚, 传播行为为REQUIRED
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(Integer id){
        //1.删除部门
        deptMapper.delete(id);
        //2.删除部门下的员工
        empMapper.deleteByDeptId(id);
    }

    public void add(Dept dept){
        /*补充基础属性*/
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    public Dept findById(Integer id){
        return deptMapper.findById(id);
    }

    public void update(Dept dept){
        /*补充基础属性*/
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
