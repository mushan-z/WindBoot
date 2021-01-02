package com.wind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
//@MapperScan("com.wind.dataBase.mybatis.mapper")
public class WindBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WindBootApplication.class, args);
	}

}
