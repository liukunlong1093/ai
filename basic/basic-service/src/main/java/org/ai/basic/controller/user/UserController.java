package org.ai.basic.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ai.basic.dto.user.req.UserPageReqDTO;
import org.ai.basic.dto.user.req.UserQueryReqDTO;
import org.ai.basic.dto.user.res.PageResDTO;
import org.ai.basic.model.base.BaseUser;
import org.ai.basic.service.user.UserService;
import org.ai.core.base.http.Response;
import org.ai.basic.dto.user.req.UserCreateReqDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户基础服务控制器
 *
 * @author: lk
 * @date: 2024/7/10 19:22
 */
@RestController
@Tag(name = "BaseUser API")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 测试接口
     *
     * @return "Hello, World!"
     */
    @GetMapping("/test")
    public Response<String> test() {
        return Response.success("Hello, World!");
    }

    /**
     * 创建用户
     *
     * @param userCreateReqDTO 用户创建请求DTO
     * @return 新用户的ID
     */
    @PostMapping
    @Operation(summary = "创建用户")
    public Response<Long> createUser(@RequestBody UserCreateReqDTO userCreateReqDTO) {
        return Response.success(userService.createUser(userCreateReqDTO));
    }

    /**
     * 分页查询用户
     *
     * @param pageReqDTO 分页请求DTO
     * @return 分页结果
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户")
    public Response<PageResDTO<BaseUser>> pageQuery(UserPageReqDTO pageReqDTO) {
        return Response.success(userService.pageQuery(pageReqDTO));
    }

    /**
     * 自定义查询用户
     *
     * @param queryReqDTO 自定义查询请求DTO
     * @return 用户列表
     */
    @GetMapping("/query")
    @Operation(summary = "自定义查询用户")
    public Response<List<BaseUser>> customQuery(UserQueryReqDTO queryReqDTO) {
        return Response.success(userService.customQuery(queryReqDTO));
    }
}