package com.wind.aspect.controller;

import com.wind.aspect.service.IAspectTestService;
import com.wind.aspect.service.impl.AspectTestServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubin
 * @time 2021/1/3 下午 12:28
 */
@Api(tags="切面测试")
@RestController
@RequestMapping("/aspect")
public class AspectTestController {

    //实现接口的
    @Autowired
    private IAspectTestService aspectTestService;
    //没有实现接口的
    @Autowired
    private AspectTestServiceImpl aspectTestServiceImpl;

    @ApiOperation(value = "aspectTest",notes = "切面测试")
    @GetMapping("/aspectTest")
    public String aspectTest(@RequestParam(value="name") String name){
        aspectTestService.show(name);
        return "成功";
    }



    @ApiOperation(value = "aspectNoInter",notes = "没有实现接口的方法aop")
    @GetMapping("/aspectNoInter")
    public String aspectNoInter(@RequestParam(value="name") String name){
        aspectTestServiceImpl.showOut(name);
        return "成功";
    }
}
