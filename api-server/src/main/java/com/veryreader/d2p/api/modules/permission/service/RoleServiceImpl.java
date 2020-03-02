package com.veryreader.d2p.api.modules.permission.service;

import com.veryreader.d2p.api.modules.permission.entity.Role;
import com.veryreader.d2p.api.modules.permission.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleResourceService roleResourceService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void authz(Long roleId, List<Long> resourceIds) {
        Role role = this.getById(roleId);
        roleResourceService.authz(roleId, role.getPlatformId(),resourceIds);
    }

    @Override
    public List<Role> getRoleList() {
        return this.list();
    }
}
