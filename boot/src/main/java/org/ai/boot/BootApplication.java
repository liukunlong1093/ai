package org.ai.boot;

import org.ai.basic.dto.user.req.UserCreateReqDTO;
import org.ai.basic.model.base.BaseUser;
import org.ai.basic.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.mybatis.spring.annotation.MapperScan;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition(info = @Info(title = "AI Project API", version = "1.0", description = "API documentation for the AI project"))
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
@MapperScan("org.ai.basic.mapper")
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
        UserCreateReqDTO userCreateReqDTO = new UserCreateReqDTO();
        userCreateReqDTO.setUsername("testuser");
        userCreateReqDTO.setPassword("password");
        userCreateReqDTO.setNickname("Test BaseUser");
        Long newBaseUserId = userService.createUser(userCreateReqDTO);
        log.info("创建用户成功: id={}", newBaseUserId);

        // 2. 查询用户
        log.info("2. 查询用户");
        BaseUser foundBaseUser = userService.getBaseUserById(newBaseUserId);
        log.info("查询到用户: {}", foundBaseUser);

        // 3. 更新用户
        log.info("3. 更新用户");
        foundBaseUser.setNickname("Updated Test BaseUser");
        userService.updateBaseUser(foundBaseUser);
        log.info("更新用户成功: {}", foundBaseUser);

        // 4. 查询所有用户
        log.info("4. 查询所有用户");
        log.info("所有用户: {}", userService.getAllBaseUsers());

        // 5. 删除用户
        log.info("5. 删除用户");
        userService.deleteBaseUser(foundBaseUser.getId());
        log.info("删除用户成功");

        // 6. 验证删除
        log.info("6. 验证删除");
        BaseUser deletedBaseUser = userService.getBaseUserById(foundBaseUser.getId());
        if (deletedBaseUser == null) {
            log.info("用户已成功删除");
        } else {
            log.error("用户删除失败");
        }

        log.info("集成测试执行完毕。");
    }
}