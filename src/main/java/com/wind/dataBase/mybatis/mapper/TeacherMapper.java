package com.wind.dataBase.mybatis.mapper;

import com.wind.dataBase.mybatis.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhoubin
 * @time 2020/12/30 下午 4:22
 */
@Repository
public interface TeacherMapper {

    Teacher selectById(@Param("id")Long id);

    Long selectCount();
}
