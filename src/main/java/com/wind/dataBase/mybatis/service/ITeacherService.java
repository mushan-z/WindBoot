package com.wind.dataBase.mybatis.service;

import com.wind.dataBase.mybatis.entity.Teacher;

/**
 * @author zhoubin
 * @time 2020/12/30 下午 5:10
 */
public interface ITeacherService {

    Teacher getById(Long id);
    Long getCount();
}
