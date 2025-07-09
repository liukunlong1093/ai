package org.ai.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Spring Boot 启动类
 * SpringBootApplication 注解包含了三个核心注解：
 * 1. @SpringBootConfiguration: 声明这是一个 Spring Boot 的配置类。
 * 2. @EnableAutoConfiguration: 开启 Spring Boot 的自动配置功能。
 * 3. @ComponentScan: 默认扫描该类所在的包（即 org.ai.boot）及其所有子包下的组件（如 @Controller, @Service, @Repository, @Component 等）。
 */
@ServletComponentScan(basePackages = {"org.ai"})
@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        System.out.println("(^---^) Spring Boot Application started successfully!");
    }

}