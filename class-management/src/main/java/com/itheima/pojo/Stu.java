package com.itheima.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RestController
public class Stu {
    private Integer id; //ID
    private String name; //用户名
    private String studentNo; //学号
    private String image; //图像url
    private Integer gender; //性别 , 1 男, 2 女
    private Integer classNo; //班级
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
