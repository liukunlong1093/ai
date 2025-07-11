package org.ai.basic.service.impl;

import org.ai.basic.common.domain.dto.req.UserCreateReqDTO;
import org.ai.basic.common.domain.entity.User;
import org.ai.basic.service.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.ai.basic.common.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Test
    void createUser() {
        // given
        UserCreateReqDTO userCreateReqDTO = new UserCreateReqDTO();
        userCreateReqDTO.setUsername("test");
        userCreateReqDTO.setPassword("password");
        userCreateReqDTO.setEmail("test@test.com");
        userCreateReqDTO.setPhone("1234567890");
        userCreateReqDTO.setNickname("test-nickname");

        // when
        userService.createUser(userCreateReqDTO);

        // then
        verify(userMapper).insert(any(User.class));
    }

    @Test
    void getUserById() {
        // given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("test");
        when(userMapper.selectById(userId)).thenReturn(user);

        // when
        User result = userService.getUserById(userId);

        // then
        assertEquals(userId, result.getId());
        assertEquals("test", result.getUsername());
        verify(userMapper).selectById(userId);
    }

    @Test
    void updateUser() {
        // given
        User user = new User();
        user.setId(1L);
        user.setUsername("updated-test");

        // when
        userService.updateUser(user);

        // then
        verify(userMapper).updateById(user);
    }

    @Test
    void getAllUsers() {
        // given
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        List<User> userList = Collections.singletonList(user);
        when(userMapper.selectList(null)).thenReturn(userList);

        // when
        List<User> result = userService.getAllUsers();

        // then
        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getUsername());
        verify(userMapper).selectList(null);
    }

    @Test
    void deleteUser() {
        // given
        Long userId = 1L;

        // when
        userService.deleteUser(userId);

        // then
        verify(userMapper).deleteById(userId);
    }
}