package com.veryreader.d2p.api.modules.permission.service;

import com.veryreader.d2p.api.modules.permission.entity.RoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
public interface RoleResourceService extends IService<RoleResource> {

    void authz(Long roleId, Long platformId, List<Long> resourceIds);

    List<Long> getResourceIdsByRoleId(Long roleId);

}
