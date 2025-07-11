package org.ai.basic.service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ai.basic.service.UserService;
import org.ai.core.base.http.Response;
import org.ai.basic.common.domain.dto.req.UserCreateReqDTO;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/test")
    public Response<String> test() {
        return Response.success("Hello, World!");
    }

    @PostMapping
    public Response<Long> createUser(@RequestBody UserCreateReqDTO userCreateReqDTO) {
        return Response.success(userService.createUser(userCreateReqDTO));
    }
}