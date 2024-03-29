package com.wind.controller;

import com.wind.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoubin
 * @time 2020/9/6 下午 8:11
 */
@Api(value = "Test接口")
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @ApiOperation(value = "getInfo",notes = "测试接口")
    @GetMapping("/getInfo")
    public Map<String,Object> getInfo(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","1");
        map.put("msg","success");
        log.info("getInfo success");
        return map;
    }

    @ApiOperation(value = "getSum",notes = "求和接口")
    @GetMapping("/getSum")
    public Map<String,Object> getSum(@RequestParam(name ="a")@ApiParam(value = "参数A") Integer a, @RequestParam(name ="b")@ApiParam(value = "参数b")Integer b){
        Map<String,Object> map = new HashMap<String,Object>();
        int c = a+b;
        map.put("code","1");
        map.put("sum",c);
        map.put("msg","成功");
        log.info("getInfo success");
        return map;
    }


    @ApiOperation(value = "arrayParamTest",notes = "参数逗号分隔，使用数组接收")
    @GetMapping("/arrayParamTest")
    public List<Long> arrayParamTest(Long[] arr){
        return Arrays.asList(arr);
    }


}
