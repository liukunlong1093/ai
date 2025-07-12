package org.ai.basic.service.impl;

import org.ai.basic.common.domain.dto.req.UserCreateReqDTO;
import org.ai.basic.service.model.base.BaseUser;
import org.ai.basic.service.mapper.BaseUserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ai.basic.common.domain.dto.req.UserPageReqDTO;
import org.ai.basic.common.domain.dto.res.PageResDTO;
 
import java.util.Collections;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 用户基础服务实现测试
 */
@ExtendWith(MockitoExtension.class)
class BaseUserServiceImplTest {

    @InjectMocks
    private BaseUserServiceImpl userService;

    @Mock
    private BaseUserMapper userMapper;

    /**
     * 测试创建用户
     */
    @Test
    void createBaseUser() {
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
        verify(userMapper).insert(any(BaseUser.class));
    }

    /**
     * 测试根据ID获取用户
     */
    @Test
    void getBaseUserById() {
        // given
        Long userId = 1L;
        BaseUser user = new BaseUser();
        user.setId(userId);
        user.setUsername("test");
        when(userMapper.selectById(userId)).thenReturn(user);

        // when
        BaseUser result = userService.getBaseUserById(userId);

        // then
        assertEquals(userId, result.getId());
        assertEquals("test", result.getUsername());
        verify(userMapper).selectById(userId);
    }

    /**
     * 测试更新用户信息
     */
    @Test
    void updateBaseUser() {
        // given
        BaseUser user = new BaseUser();
        user.setId(1L);
        user.setUsername("updated-test");

        // when
        userService.updateBaseUser(user);

        // then
        verify(userMapper).updateById(user);
    }

    /**
     * 测试获取所有用户
     */
    @Test
    void getAllBaseUsers() {
        // given
        BaseUser user = new BaseUser();
        user.setId(1L);
        user.setUsername("test");
        List<BaseUser> userList = Collections.singletonList(user);
        when(userMapper.selectList(null)).thenReturn(userList);

        // when
        List<BaseUser> result = userService.getAllBaseUsers();

        // then
        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getUsername());
        verify(userMapper).selectList(null);
    }

    /**
     * 测试根据ID删除用户
     */
    @Test
    void deleteBaseUser() {
        // given
        Long userId = 1L;
 
        // when
        userService.deleteBaseUser(userId);
 
        // then
        verify(userMapper).deleteById(userId);
    }
 
    /**
     * 测试分页查询用户
     */
    @Test
    void pageQuery() {
        // given
        UserPageReqDTO pageReqDTO = new UserPageReqDTO();
        pageReqDTO.setPage(1);
        pageReqDTO.setSize(10);
        pageReqDTO.setUsername("test");
 
        Page<BaseUser> page = new Page<>(pageReqDTO.getPage(), pageReqDTO.getSize());
        List<BaseUser> userList = Collections.singletonList(new BaseUser());
        page.setRecords(userList);
        page.setTotal(1);
 
        when(userMapper.selectPage(any(Page.class), any(QueryWrapper.class))).thenReturn(page);
 
        // when
        PageResDTO<BaseUser> result = userService.pageQuery(pageReqDTO);
 
        // then
        assertNotNull(result);
        assertEquals(1, result.getTotal());
        assertEquals(1, result.getRecords().size());
        verify(userMapper, times(1)).selectPage(any(Page.class), any(QueryWrapper.class));
    }
 
    /**
     * 测试自定义查询用户
     */
    @Test
    void customQuery() {
        // given
        String query = "test";
        String sort = "username";
        List<BaseUser> userList = Collections.singletonList(new BaseUser());
        when(userMapper.selectList(any(QueryWrapper.class))).thenReturn(userList);
 
        // when
        List<BaseUser> result = userService.customQuery(query, sort);
 
        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userMapper, times(1)).selectList(any(QueryWrapper.class));
    }
}