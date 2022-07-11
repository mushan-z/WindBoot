package com.wind.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class TestInit2 implements CommandLineRunner, Ordered {
    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("实现接口：CommandLineRunner,Ordered");
    }
}
