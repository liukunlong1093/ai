package org.ai.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ai.basic.model.base.BaseUser;
import org.apache.ibatis.annotations.Mapper;
import org.ai.basic.dto.user.req.UserQueryReqDTO;

import java.util.List;

/**
 * 用户表 Mapper 接口
 */
@Mapper
public interface BaseUserMapper extends BaseMapper<BaseUser> {

    List<BaseUser> customQuery(UserQueryReqDTO queryReqDTO);

}