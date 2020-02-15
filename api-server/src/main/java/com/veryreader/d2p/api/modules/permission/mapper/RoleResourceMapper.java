package com.veryreader.d2p.api.modules.permission.mapper;

import com.veryreader.d2p.api.modules.permission.entity.RoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    List<Long> selectResourceIds(@Param("roleId") Long roleId);
}
