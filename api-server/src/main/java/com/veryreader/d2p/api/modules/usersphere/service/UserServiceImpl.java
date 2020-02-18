package com.veryreader.d2p.api.modules.usersphere.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.veryreader.d2p.api.modules.permission.service.UserRoleService;
import com.veryreader.d2p.api.modules.usersphere.entity.User;
import com.veryreader.d2p.api.modules.usersphere.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return userRoleService.getRoleIdsByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void authz(Long userId, List<Long> roleIds) {
        userRoleService.authz(userId,roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(User user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(RandomUtil.randomString(6));
        }
        generatePassword(user);
        this.save(user);
    }

    private void generatePassword(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(User user) {
        if(StringUtils.isNotBlank(user.getPassword())){
            generatePassword(user);
        }else{
            user.setPassword(null);
        }
        user.setUsername(null);//禁止修改用户名
        this.updateById(user);
        return false;
    }

    @Override
    public User getByUsername(String username, boolean withRoles) {
        if(StringUtils.isBlank(username)){
            return null;
        }
        User query = new User();
        query.setUsername(username);
        User user = baseMapper.selectOne(Wrappers.lambdaQuery(query));
        if(withRoles){
            List<Long> roleIds = getUserRoleIds(user.getId());
            user.setRoles(roleIds);

        }
        return user;
    }

    @Override
    public boolean verifyPassword(User user, String rawPassword) {
        String encodePassword = passwordEncoder.encode(rawPassword);
        if(StringUtils.equals(encodePassword,user.getPassword())){
            return true;
        }
        return false;
    }


}
