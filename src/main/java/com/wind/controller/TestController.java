package com.wind.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoubin
 * @time 2020/9/6 下午 8:11
 */
@Api(value = "Test接口")
@RestController
@RequestMapping("/wind/test")
@Slf4j
public class TestController {


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
}
