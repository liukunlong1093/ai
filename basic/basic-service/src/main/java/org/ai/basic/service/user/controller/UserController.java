package org.ai.basic.service.user.controller;

import org.ai.basic.model.base.BaseUser;
import org.ai.basic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public BaseUser getUserById(@PathVariable("id") Long id) {
        return userService.getBaseUserById(id);
    }
}
