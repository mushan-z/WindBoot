package com.wind.dataBase.mybatis.service;

import com.wind.dataBase.mybatis.entity.Teacher;

import java.util.List;

/**
 * @author zhoubin
 * @time 2020/12/30 下午 5:10
 */
public interface ITeacherService {

    List<Teacher> getList(Teacher teacher);
    List<Teacher> getList2(Teacher teacher);
    Teacher getById(Long id);
    void save(Teacher teacher);
    Long getCount();
    void updateById(Teacher teacher);
    void updateById2(Teacher teacher);
    void deleteById(Long id);
}
