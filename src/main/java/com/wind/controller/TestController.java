package com.wind.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoubin
 * @time 2020/9/6 下午 8:11
 */
@RestController
@RequestMapping("/wind/test")
@Slf4j
public class TestController {


    @RequestMapping("/getInfo")
    public Map<String,Object> getInfo(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","1");
        map.put("msg","success");
        log.info("getInfo success");
        return map;
    }
}
