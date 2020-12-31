package com.wind.dataBase.mybatis.mapper;

import com.wind.dataBase.mybatis.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoubin
 * @time 2020/12/30 下午 4:22
 */
@Repository
public interface TeacherMapper {

    Teacher selectById(@Param("id")Long id);

    List<Teacher> selectList(Teacher teacher);

    List<Teacher> selectList2(Teacher teacher);

    Long selectCount();

    void insert(Teacher teacher);

    void updateById(Teacher teacher);

    void updateById2(Teacher teacher);

    void deleteById(@Param("id") Long id);
}
