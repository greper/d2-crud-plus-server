package com.veryreader.d2p.api.modules.user.controller;

import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.user.entity.Menu;
import com.veryreader.d2p.api.modules.user.entity.vo.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/8/30$
 */
@RestController
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    /**
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Ret getInfo() {
        User user = new User();
        user.setId("1");
        user.setName("admin");
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("test");
        user.setRoles(roles);
        return Ret.success("",user);
    }
    /**
     * @return
     */
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public Ret getPermissions() {
        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setComponent("/test");
        menu.setName("测试");
        menu.setPath("/test");
        menu.setPermission("sys_test_view");
        menus.add(menu);

        return Ret.success("",menus);
    }

}
