package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.impl.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录用户: {}",emp);
        Emp e = empService.login(emp);
        //登录成功,生成并下发令牌
        if(e!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);//jwt令牌包含了当前登录员工的信息
            return Result.success(jwt);
        }

        //登录失败,返回错误信息
        return Result.error("用户名或密码错误");
    }
}
