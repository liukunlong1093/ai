package org.ai.boot;

import org.ai.basic.common.domain.entity.User;
import org.ai.basic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot 启动类
 * SpringBootApplication 注解包含了三个核心注解：
 * 1. @SpringBootConfiguration: 声明这是一个 Spring Boot 的配置类。
 * 2. @EnableAutoConfiguration: 开启 Spring Boot 的自动配置功能。
 * 3. @ComponentScan: 默认扫描该类所在的包（即 org.ai.boot）及其所有子包下的组件（如 @Controller, @Service, @Repository, @Component 等）。
 */
@ServletComponentScan(basePackages = {"org.ai"})
@SpringBootApplication
@ComponentScan(basePackages = {"org.ai"})
@MapperScan("org.ai.basic.service.mapper")
public class BootApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BootApplication.class);

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        System.out.println("(^---^) Spring Boot Application started successfully!");
    }

    @Override
    public void run(String... args) {
        log.info("开始执行集成测试...");

        // 1. 创建用户
        log.info("1. 创建用户");
        User newUser = new User();
        newUser.setUsername("testuser");
        newUser.setPassword("password");
        newUser.setNickname("Test User");
        newUser.setDeleted(false);
        userService.createUser(newUser);
        log.info("创建用户成功: {}", newUser);

        // 2. 查询用户
        log.info("2. 查询用户");
        User foundUser = userService.getUserById(newUser.getId());
        log.info("查询到用户: {}", foundUser);

        // 3. 更新用户
        log.info("3. 更新用户");
        foundUser.setNickname("Updated Test User");
        userService.updateUser(foundUser);
        log.info("更新用户成功: {}", foundUser);

        // 4. 查询所有用户
        log.info("4. 查询所有用户");
        log.info("所有用户: {}", userService.getAllUsers());

        // 5. 删除用户
        log.info("5. 删除用户");
        userService.deleteUser(foundUser.getId());
        log.info("删除用户成功");

        // 6. 验证删除
        log.info("6. 验证删除");
        User deletedUser = userService.getUserById(foundUser.getId());
        if (deletedUser == null) {
            log.info("用户已成功删除");
        } else {
            log.error("用户删除失败");
        }

        log.info("集成测试执行完毕。");
    }
}