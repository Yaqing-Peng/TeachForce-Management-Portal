package com.itheima.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RestController
public class Comment {
    private Integer id; //ID
    private Integer studentNo; //学生ID
    private String content; //评论内容
    private Integer type; //评论类型 1加分 2 减分
    private Integer points; //分数
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
