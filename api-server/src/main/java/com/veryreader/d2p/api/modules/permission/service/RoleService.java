package com.veryreader.d2p.api.modules.permission.service;

import com.veryreader.d2p.api.modules.permission.entity.Role;
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
public interface RoleService extends IService<Role> {

    /**
     * 给角色授权权限
     * @param roleId
     * @param resourceIds
     */
    void authz(Long roleId, List<Long> resourceIds);
}
