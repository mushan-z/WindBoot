package com.wind.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhoubin
 * @time 2020/9/6 下午 9:58
 */
@Data
public class Student {
    @ApiModelProperty(value="ID")
    private Long id;
    private String name;
    private Integer age;
}
