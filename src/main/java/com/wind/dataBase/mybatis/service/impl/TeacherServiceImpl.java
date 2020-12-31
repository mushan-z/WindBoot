package com.wind.dataBase.mybatis.service.impl;

import com.wind.dataBase.mybatis.entity.Teacher;
import com.wind.dataBase.mybatis.mapper.TeacherMapper;
import com.wind.dataBase.mybatis.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoubin
 * @time 2020/12/30 下午 5:10
 */
@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getById(Long id) {
        return teacherMapper.selectById(id);
    }

    @Override
    public Long getCount() {
        return teacherMapper.selectCount();
    }
}
