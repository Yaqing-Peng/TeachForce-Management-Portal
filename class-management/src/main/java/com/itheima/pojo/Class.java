package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    private Integer id;
    private String classNo;
    private Integer studentSum;
    private Integer chineseTeacherId;
    private Integer englishTeacherId;
    private Integer mathTeacherId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
