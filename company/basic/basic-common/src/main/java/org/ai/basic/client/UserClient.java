package org.ai.basic.client;

import org.ai.basic.model.base.BaseUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "basic-service", path = "/user")
public interface UserClient {

    @GetMapping("/{id}")
    BaseUser getUserById(@PathVariable("id") Long id);

}
