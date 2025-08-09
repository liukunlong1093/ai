package org.ai.basic.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ai.basic.dto.user.req.UserCreateReqDTO;
import org.ai.basic.dto.user.req.UserPageReqDTO;
import org.ai.basic.dto.user.req.UserQueryReqDTO;
import org.ai.basic.dto.user.res.PageResDTO;
import org.ai.basic.mapper.BaseUserMapper;
import org.ai.basic.model.base.BaseUser;
import org.ai.basic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户基础服务实现
 *
 * @author: lk
 * @date: 2024/7/10 19:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private static final Set<String> ALLOWED_SORT_FIELDS = new HashSet<>(Arrays.asList(
            "id", "username", "nickname", "email", "phone", "status",
            "creator", "updater", "createTime", "updateTime"
    ));

    @Autowired
    private BaseUserMapper userMapper;

    /**
     * 创建新用户
     *
     * @param userCreateReqDTO 用户创建请求DTO
     * @return 新用户的ID
     */
    @Override
    public Long createUser(UserCreateReqDTO userCreateReqDTO) {
        BaseUser user = new BaseUser();
        user.setUsername(userCreateReqDTO.getUsername());
        user.setPassword("password");
        user.setNickname(userCreateReqDTO.getNickname());
        user.setDeleted(false);
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 根据ID获取用户
     *
     * @param id 用户ID
     * @return 用户实体
     */
    @Override
    public BaseUser getBaseUserById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户实体
     */
    @Override
    public void updateBaseUser(BaseUser user) {
        userMapper.updateById(user);
    }

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    @Override
    public List<BaseUser> getAllBaseUsers() {
        return userMapper.selectList(null);
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     */
    @Override
    public void deleteBaseUser(Long id) {
        userMapper.deleteById(id);
    }

    /**
     * 分页查询用户
     *
     * @param pageReqDTO 分页请求DTO
     * @return 分页结果
     */
    @Override
    public PageResDTO<BaseUser> pageQuery(UserPageReqDTO pageReqDTO) {
        Page<BaseUser> page = new Page<>(pageReqDTO.getPage(), pageReqDTO.getSize());
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(pageReqDTO.getUsername())) {
            queryWrapper.like("username", pageReqDTO.getUsername());
        }
        if (StringUtils.hasText(pageReqDTO.getNickname())) {
            queryWrapper.like("nickname", pageReqDTO.getNickname());
        }
        Page<BaseUser> result = userMapper.selectPage(page, queryWrapper);
        return new PageResDTO<>(result.getTotal(), result.getRecords());
    }

    /**
     * 自定义查询
     *
     * @param queryReqDTO 自定义查询DTO
     * @return 用户列表
     */
    @Override
    public List<BaseUser> customQuery(UserQueryReqDTO queryReqDTO) {
        if (StringUtils.hasText(queryReqDTO.getSort()) && !ALLOWED_SORT_FIELDS.contains(queryReqDTO.getSort())) {
            throw new IllegalArgumentException("Invalid sort field: " + queryReqDTO.getSort());
        }
        return userMapper.customQuery(queryReqDTO);
    }
}