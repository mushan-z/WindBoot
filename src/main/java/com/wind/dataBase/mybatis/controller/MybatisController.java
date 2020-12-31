package com.wind.dataBase.mybatis.controller;

import com.wind.dataBase.jdbc.entity.User;
import com.wind.dataBase.mybatis.entity.Teacher;
import com.wind.dataBase.mybatis.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @ApiOperation(value = "getTeacherList",notes = "查询教师List")
    @GetMapping("/getTeacherList")
    public List<Teacher> getTeacherList(Teacher teacher){
        List<Teacher> list = teacherService.getList(teacher);
        return list;
    }

    @ApiOperation(value = "getTeacherList2",notes = "查询教师List")
    @GetMapping("/getTeacherList2")
    public List<Teacher> getTeacherList2(Teacher teacher){
        List<Teacher> list = teacherService.getList2(teacher);
        return list;
    }

    @ApiOperation(value = "getTeacherById",notes = "获取教师")
    @GetMapping("/getTeacherById")
    public Teacher getTeacherById(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){
//        Long count = teacherService.getCount();
//        System.out.println("count:"+count);
        Teacher teacher = new Teacher();
        teacher = teacherService.getById(id);
        return teacher;
    }


    @ApiOperation(value = "addTeacher",notes = "添加教师")
    @PostMapping("/addTeacher")
    public String addTeacher(@RequestBody Teacher teacher){
        teacher.setCreateDate(Optional.ofNullable(teacher.getCreateDate()).orElse(new Date()));
        teacherService.save(teacher);
        return "成功";
    }

    @ApiOperation(value = "updateTeacher",notes = "修改教师")
    @PostMapping("/updateTeacher")
    public String updateTeacher(@RequestBody Teacher teacher){
        teacher.setCreateDate(Optional.ofNullable(teacher.getCreateDate()).orElse(new Date()));
        teacherService.updateById(teacher);
        return "成功";
    }

    @ApiOperation(value = "updateTeacher2",notes = "修改教师")
    @PostMapping("/updateTeacher2")
    public String updateTeacher2(@RequestBody Teacher teacher){
        teacher.setCreateDate(Optional.ofNullable(teacher.getCreateDate()).orElse(new Date()));
        teacherService.updateById2(teacher);
        return "成功";
    }


    @ApiOperation(value = "deleteTeacherById",notes = "删除教师")
    @GetMapping("/deleteTeacherById")
    public String deleteTeacherById(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){
        teacherService.deleteById(id);
        return "成功";
    }


}
