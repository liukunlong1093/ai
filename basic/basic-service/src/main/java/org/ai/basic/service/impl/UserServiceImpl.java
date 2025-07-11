package org.ai.basic.service.impl;

import org.ai.basic.common.domain.dto.req.UserCreateReqDTO;
import org.ai.basic.common.domain.entity.User;
import org.ai.basic.service.UserService;
import org.ai.basic.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lk
 * @date: 2024/7/10 19:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long createUser(UserCreateReqDTO userCreateReqDTO) {
        User user = new User();
        user.setUsername(userCreateReqDTO.getUsername());
        user.setPassword(userCreateReqDTO.getPassword());
        user.setEmail(userCreateReqDTO.getEmail());
        user.setPhone(userCreateReqDTO.getPhone());
        user.setNickname(userCreateReqDTO.getNickname());
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }
}