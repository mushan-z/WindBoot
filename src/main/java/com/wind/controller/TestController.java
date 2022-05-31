package com.wind.controller;

import com.wind.entity.Student;
import com.wind.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubin
 * @time 2020/9/6 下午 8:11
 */
@Api(value = "Test接口")
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private StudentService studentService;



    @ApiOperation(value = "asyncTest",notes = "异步检验")
    @GetMapping("/asyncTest")
    public Student asyncTest(){
        Student student = new Student();
        Future<Integer> ageResult = studentService.getStudentAge(1L);
        Future<String> nameResult = studentService.getStudentName(1L);
        try{
            while(true){
                TimeUnit.SECONDS.sleep(1);
                if(ageResult.get()!=null&&!StringUtils.isEmpty(nameResult.get())){
                    student.setAge(ageResult.get());
                    student.setName(nameResult.get());
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return student;
    }


    @ApiOperation(value = "asyncTest2",notes = "异步检验")
    @GetMapping("/asyncTest2")
    public Student asyncTest2(){
        Student student = new Student();
        Future<Integer> ageResult = studentService.getStudentAge(1L);
        Future<String> nameResult = studentService.getStudentName(1L);
        try{
            while(true){
                TimeUnit.SECONDS.sleep(1);
                if(ageResult.isDone()&&nameResult.isDone()){
                    student.setAge(ageResult.get());
                    student.setName(nameResult.get());
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return student;
    }


}
