package org.ai.boss.controller;

import org.ai.basic.client.UserClient;
import org.ai.basic.model.base.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/user/{id}")
    public BaseUser testGetUserById(@PathVariable("id") Long id) {
        // This is the remote call to basic-service
        return userClient.getUserById(id);
    }
}
