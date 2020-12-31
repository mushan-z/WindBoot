package com.wind.dataBase.mybatis.controller;

import com.wind.dataBase.jdbc.entity.User;
import com.wind.dataBase.mybatis.entity.Teacher;
import com.wind.dataBase.mybatis.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubin
 * @time 2020/12/30 下午 4:21
 */
@Api(tags="mybatis接口")
@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private ITeacherService teacherService;

    @ApiOperation(value = "getTeacherById",notes = "获取教师")
    @GetMapping("/getTeacherById")
    public Teacher getTeacherById(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){
//        Long count = teacherService.getCount();
//        System.out.println("count:"+count);
        Teacher teacher = new Teacher();
        teacher = teacherService.getById(id);
        return teacher;
    }


}
