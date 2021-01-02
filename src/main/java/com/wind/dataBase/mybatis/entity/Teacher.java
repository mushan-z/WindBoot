package com.wind.dataBase.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhoubin
 * @time 2020/12/27 上午 11:49
 */
@Data
@Alias("teacher")
public class Teacher {

    private Long id;
    private String subject;
    private String name;
    private Integer age;
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
