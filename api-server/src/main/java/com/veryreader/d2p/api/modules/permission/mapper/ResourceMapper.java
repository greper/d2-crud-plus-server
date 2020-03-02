package com.veryreader.d2p.api.modules.permission.mapper;

import com.veryreader.d2p.api.modules.permission.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> selectByRoleIds(@Param("roleIds")  List<Long> roleIds , @Param("platformId")  Long platformId);
}
