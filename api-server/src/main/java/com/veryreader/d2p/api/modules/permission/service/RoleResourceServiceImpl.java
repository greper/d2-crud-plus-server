package com.veryreader.d2p.api.modules.permission.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.veryreader.d2p.api.modules.permission.entity.RoleResource;
import com.veryreader.d2p.api.modules.permission.mapper.RoleResourceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void authz(Long roleId,  List<Long> resourceIds) {
        //先删除roleId所有的权限
        RoleResource query = new RoleResource();
        query.setRoleId(roleId);
        baseMapper.delete(Wrappers.lambdaQuery(query));
        // 然后再添加
        for (Long resourceId : resourceIds) {
            RoleResource add = new RoleResource();
            add.setRoleId(roleId);
            add.setResourceId(resourceId);
            baseMapper.insert(add);
        }
    }

    @Override
    public List<Long> getResourceIdsByRoleId(Long roleId) {
        return baseMapper.selectResourceIds(roleId);
    }
}
