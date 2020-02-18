package com.veryreader.d2p.api.modules.permission.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.veryreader.d2p.api.modules.permission.entity.RoleResource;
import com.veryreader.d2p.api.modules.permission.entity.UserRole;
import com.veryreader.d2p.api.modules.permission.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<UserRole> getByUserIds(Collection<Long> userIds) {
        if(userIds == null || userIds.size() == 0){
            return new ArrayList<>(0);
        }
        LambdaQueryWrapper<UserRole> query = Wrappers.lambdaQuery();
        query.in(UserRole::getUserId,userIds);
        return baseMapper.selectList(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void authz(Long userId, List<Long> roleIds) {
        //先删除roleId所有的权限
        UserRole query = new UserRole();
        query.setUserId(userId);
        baseMapper.delete(Wrappers.lambdaQuery(query));
        // 然后再添加
        for (Long roleId : roleIds) {
            UserRole add = new UserRole();
            add.setRoleId(roleId);
            add.setUserId(userId);
            baseMapper.insert(add);
        }
    }
}
