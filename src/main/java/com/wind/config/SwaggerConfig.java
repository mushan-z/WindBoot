package com.wind.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhoubin
 * @time 2020/9/7 上午 11:33
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createSwaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                //添加文档属性
                .apiInfo(getApiInfo())
                .select()
                //此包下的路径才会生成接口
                .apis(RequestHandlerSelectors.basePackage("com.wind"))
                //加了ApiOperation注解的方法才会生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
                //全局参数
                //.globalOperationParameters();
    }

    /**
     * 添加文档的一些属性
     * @return
     */
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("WindBoot API文档")
                .description("欢迎使用WindBoot")
                .version("1.0")
                .build();
    }
}
