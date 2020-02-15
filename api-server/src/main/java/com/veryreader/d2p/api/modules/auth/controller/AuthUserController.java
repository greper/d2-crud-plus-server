package com.veryreader.d2p.api.modules.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.permission.entity.Resource;
import com.veryreader.d2p.api.modules.permission.service.ResourceService;
import com.veryreader.d2p.api.modules.usersphere.entity.User;
import com.veryreader.d2p.api.modules.usersphere.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class AuthUserController {

    private final ResourceService resourceService;
    private final UserService userService;

    /**
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Ret getInfo() {
        User user = getLoginUser();
        return Ret.success("",user);
    }

    private User getLoginUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setNickname("管理员");
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("test");
        user.setRoles(roles);
        return user;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public Ret getPermissions() {
        User user = getLoginUser();
        List<Long> roleIds = userService.getUserRoleIds(user.getId());
        List<Resource> list = resourceService.findResourceTreeByRoleIds(roleIds);
        return Ret.success("",list);
    }

}
