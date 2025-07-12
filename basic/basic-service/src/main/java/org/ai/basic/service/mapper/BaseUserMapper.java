package org.ai.basic.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ai.basic.service.model.base.BaseUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 Mapper 接口
 */
@Mapper
public interface BaseUserMapper extends BaseMapper<BaseUser> {

}