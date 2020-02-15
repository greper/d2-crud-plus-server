package com.veryreader.d2p.api.modules.permission.mapper;

import com.veryreader.d2p.api.modules.permission.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Long> selectByUserId(Long userId);

}
