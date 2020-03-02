package com.veryreader.d2p.api.modules.permission.service;

import com.veryreader.d2p.api.modules.permission.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
public interface ResourceService extends IService<Resource> {

    List<Resource> findResourceTree(Resource query);

    List<Resource> findResourceTreeByRoleIds(List<Long> roleIds, Long platformId);
}
