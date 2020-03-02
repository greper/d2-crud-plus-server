package com.veryreader.d2p.api.modules.auth.controller;


import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.permission.entity.Platform;
import com.veryreader.d2p.api.modules.permission.entity.Resource;
import com.veryreader.d2p.api.modules.permission.service.PlatformService;
import com.veryreader.d2p.api.modules.permission.service.ResourceService;
import com.veryreader.d2p.api.modules.usersphere.entity.User;
import com.veryreader.d2p.api.modules.usersphere.service.UserService;
import com.veryreader.d2p.api.security.vo.LoginUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
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
    private final PlatformService platformService;

    /**
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Ret getInfo(Authentication authentication) {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = userService.getById(loginUser.getId());
        return Ret.success("",user);
    }



    /**
     * @return
     */
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public Ret getPermissions(Authentication authentication,@RequestParam String platformCode) {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<Long> roleIds = userService.getUserRoleIds(loginUser.getId());
        Platform platform = platformService.getByCode(platformCode);
        List<Resource> list = resourceService.findResourceTreeByRoleIds(roleIds,platform.getId());
        return Ret.success("",list);
    }

}
