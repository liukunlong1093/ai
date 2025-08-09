package org.ai.basic.service.user.impl;

import org.ai.basic.dto.user.req.UserQueryReqDTO;
import org.ai.basic.mapper.BaseUserMapper;
import org.ai.basic.model.base.BaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UserServiceImpl customQuery 测试")
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private BaseUserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("当排序字段无效时应抛出 IllegalArgumentException")
    void customQuery_shouldThrowException_whenSortFieldIsInvalid() {
        // Arrange
        UserQueryReqDTO queryReqDTO = new UserQueryReqDTO();
        queryReqDTO.setSort("invalid_field");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.customQuery(queryReqDTO);
        });

        assertEquals("Invalid sort field: invalid_field", exception.getMessage());
        verify(userMapper, never()).customQuery(any());
    }

    @Test
    @DisplayName("当排序字段有效时应返回用户列表")
    void customQuery_shouldReturnUserList_whenSortFieldIsValid() {
        // Arrange
        UserQueryReqDTO queryReqDTO = new UserQueryReqDTO();
        queryReqDTO.setSort("username");

        BaseUser user = new BaseUser();
        user.setId(1L);
        user.setUsername("testuser");
        List<BaseUser> expectedUsers = Collections.singletonList(user);

        when(userMapper.customQuery(queryReqDTO)).thenReturn(expectedUsers);

        // Act
        List<BaseUser> actualUsers = userService.customQuery(queryReqDTO);

        // Assert
        assertEquals(expectedUsers, actualUsers);
        verify(userMapper, times(1)).customQuery(queryReqDTO);
    }

    @Test
    @DisplayName("当提供了查询条件时应返回匹配的用户列表")
    void customQuery_shouldReturnMatchingUsers_whenQueryIsProvided() {
        // Arrange
        UserQueryReqDTO queryReqDTO = new UserQueryReqDTO();
        queryReqDTO.setQuery("test");

        BaseUser user = new BaseUser();
        user.setId(1L);
        user.setUsername("testuser");
        List<BaseUser> expectedUsers = Collections.singletonList(user);

        when(userMapper.customQuery(queryReqDTO)).thenReturn(expectedUsers);

        // Act
        List<BaseUser> actualUsers = userService.customQuery(queryReqDTO);

        // Assert
        assertEquals(expectedUsers, actualUsers);
        verify(userMapper, times(1)).customQuery(queryReqDTO);
    }
}