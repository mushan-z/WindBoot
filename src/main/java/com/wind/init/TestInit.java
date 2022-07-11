package com.wind.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import org.springframework.boot.ApplicationRunner;
@Component
@Order(1)
public class TestInit implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("实现ApplicationRunner接口");
    }
}
