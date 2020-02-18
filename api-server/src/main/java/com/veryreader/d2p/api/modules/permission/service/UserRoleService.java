package com.veryreader.d2p.api.modules.permission.service;

import com.veryreader.d2p.api.modules.permission.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
public interface UserRoleService extends IService<UserRole> {

    List<Long> getRoleIdsByUserId(Long userId);

    List<UserRole> getByUserIds(Collection<Long> userIds);

    public void authz(Long userId, List<Long> roleIds);
}
