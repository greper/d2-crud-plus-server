package com.veryreader.d2p.api.modules.usersphere.service;

import com.veryreader.d2p.api.modules.permission.service.UserRoleService;
import com.veryreader.d2p.api.modules.usersphere.entity.User;
import com.veryreader.d2p.api.modules.usersphere.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserRoleService userRoleService;

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return userRoleService.getRoleIdsByUserId(userId);
    }
}
