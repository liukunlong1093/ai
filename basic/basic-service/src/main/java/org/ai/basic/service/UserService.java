package org.ai.basic.service;

import org.ai.basic.common.domain.dto.req.UserCreateReqDTO;
import org.ai.basic.common.domain.entity.User;

import java.util.List;

/**
 * @author: lk
 * @date: 2024/7/10 19:20
 */
public interface UserService {

    Long createUser(UserCreateReqDTO userCreateReqDTO);

    User getUserById(Long id);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);
}