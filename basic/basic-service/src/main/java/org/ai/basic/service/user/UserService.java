package org.ai.basic.service.user;

import org.ai.basic.dto.user.req.UserCreateReqDTO;
import org.ai.basic.dto.user.req.UserPageReqDTO;
import org.ai.basic.dto.user.req.UserQueryReqDTO;
import org.ai.basic.dto.user.res.PageResDTO;
import org.ai.basic.model.base.BaseUser;

import java.util.List;

/**
 * 用户基础服务接口
 *
 * @author: lk
 * @date: 2024/7/10 19:20
 */
public interface UserService {

    /**
     * 创建新用户
     *
     * @param userCreateReqDTO 用户创建请求DTO
     * @return 新用户的ID
     */
    Long createUser(UserCreateReqDTO userCreateReqDTO);

    /**
     * 根据ID获取用户
     *
     * @param id 用户ID
     * @return 用户实体
     */
    BaseUser getBaseUserById(Long id);

    /**
     * 更新用户信息
     *
     * @param user 用户实体
     */
    void updateBaseUser(BaseUser user);

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    List<BaseUser> getAllBaseUsers();

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     */
    void deleteBaseUser(Long id);

    /**
     * 分页查询用户
     *
     * @param pageReqDTO 分页请求DTO
     * @return 分页结果
     */
    PageResDTO<BaseUser> pageQuery(UserPageReqDTO pageReqDTO);

    /**
     * 自定义查询
     *
     * @param queryReqDTO 自定义查询DTO
     * @return 用户列表
     */
    List<BaseUser> customQuery(UserQueryReqDTO queryReqDTO);
}