package com.wind.service.impl;

import com.wind.service.StudentService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Override
    @Async
    public Future<String> getStudentName(Long id) {
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new AsyncResult<String>("张三");
    }

    @Override
    @Async
    public Future<Integer> getStudentAge(Long id) {
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new AsyncResult<Integer>(7);
    }
}
