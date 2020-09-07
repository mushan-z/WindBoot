package com.wind.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wind.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoubin
 * @time 2020/9/7 下午 4:42
 */
@Api("登录接口")
@RestController("/login")
@Slf4j
public class LoginController {


    @ApiOperation("登录接口")
    @PostMapping
    public Map<String,Object> login(@RequestParam(name="userName") @ApiParam(value="用户名") String userName, @RequestParam(name="password") @ApiParam(value="密码") String password){
         Map<String,Object> result = new HashMap<>();
         String token = JWTUtil.getToken(userName,password);
         result.put("code",1);
         result.put("token",token);
         result.put("msg","登录成功");
         JWTUtil.verify(token,userName,password);
         JWTUtil.getUserName(token);
         return result;
    }

    @ApiOperation(value = "checkToken",notes = "检验token")
    @GetMapping("/checkToken")
    public Map<String,Object> checkToken(@RequestParam(name ="token")@ApiParam(value = "参数token") String token,
                                         @RequestParam(name="userName") @ApiParam(value="用户名") String userName,
                                         @RequestParam(name="password") @ApiParam(value="密码") String password){
        Map<String,Object> map = new HashMap<String,Object>();
        JWTUtil.verify(token,userName,password);
        map.put("code","1");
        map.put("msg","success");
        return map;
    }


}
