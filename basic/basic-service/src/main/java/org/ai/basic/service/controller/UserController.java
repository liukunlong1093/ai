package org.ai.basic.service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ai.basic.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lk
 * @date: 2024/7/10 19:22
 */
@RestController
@Tag(name = "User API")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

}