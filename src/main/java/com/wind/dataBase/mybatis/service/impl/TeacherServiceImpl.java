package com.wind.dataBase.mybatis.service.impl;

import com.wind.dataBase.mybatis.entity.Teacher;
import com.wind.dataBase.mybatis.mapper.TeacherMapper;
import com.wind.dataBase.mybatis.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoubin
 * @time 2020/12/30 下午 5:10
 */
@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getList(Teacher teacher) {
        return teacherMapper.selectList(teacher);
    }

    @Override
    public List<Teacher> getList2(Teacher teacher) {
        return teacherMapper.selectList2(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherMapper.selectById(id);
    }

    @Override
    public Long getCount() {
        return teacherMapper.selectCount();
    }

    @Override
    public void save(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    @Override
    public void updateById(Teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    @Override
    public void deleteById(Long id) {
        teacherMapper.deleteById(id);
    }

    @Override
    public void updateById2(Teacher teacher) {
        teacherMapper.updateById2(teacher);
    }
}
