package com.wind.dataBase.mybatis.common;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhoubin
 * @time 2020/12/31 下午 4:23
 * 不使用MapperScanner时可以使用WindBootApplication上的@MapperScan
 */
@Component
public class MapperScanner {

    @Bean
    public MapperScannerConfigurer mapperScannerConfig() {
        MapperScannerConfigurer config = new MapperScannerConfigurer();
        config.setSqlSessionFactoryBeanName("sqlSessionFactory");
        config.setBasePackage("com.wind.dataBase.mybatis.mapper");
        return config;
    }
}
