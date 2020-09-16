package com.veryreader.d2p.api.modules.usersphere.controller.manager;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.base.controller.AbstractCrudController;
import com.veryreader.d2p.api.modules.permission.entity.Role;
import com.veryreader.d2p.api.modules.permission.entity.UserRole;
import com.veryreader.d2p.api.modules.permission.service.RoleService;
import com.veryreader.d2p.api.modules.permission.service.UserRoleService;
import com.veryreader.d2p.api.modules.usersphere.entity.User;
import com.veryreader.d2p.api.modules.usersphere.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/usersphere/manager/user")
@AllArgsConstructor
public class UserController extends AbstractCrudController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @GetMapping("/page")
    public Ret getUserPage(Page<User> page, User query
            , @RequestParam(value = "dateRange", required = false) String dateRange
    ) {

        setDefaultSort(page, "id", false);
        // setDefaultSort(page, "create_time", true); //实际项目中可以配置为按添加时间倒序排序

        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(query);
        // betweenDateRange(wrapper, User::getCreateTime, dateRange);
        // wrapper.eq(User::getDelFlag, '0');


        Page<User> ret = userService.page(page, wrapper);
        Map<Long,User> userMap = ret.getRecords().stream().collect(Collectors.toMap(User::getId, item->item));
        List<UserRole> userRoles = userRoleService.getByUserIds(userMap.keySet());
        for (UserRole item : userRoles) {
            User user = userMap.get(item.getUserId());
            if(user!= null){
                user.addRole(item.getRoleId());
            }
        }
        return Ret.success("", ret);
    }

    /**
     * 通过id查询
     *
     * @param id id
     * @return Ret
     */
    @GetMapping("/info")
    public Ret getById(@RequestParam("id") Long id) {
        User user = userService.getById(id);
        user.setPassword(null);
        return Ret.success("", user);
    }

    /**
     * 新增
     *
     * @param user
     * @return Ret
     */
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('usersphere:user:add')")
    public Ret save(@RequestBody User user) {
        userService.add(user);
        return Ret.success("", user.getId());
    }

    /**
     * 修改
     *
     * @param user
     * @return Ret
     */
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('usersphere:user:edit')")
    public Ret updateById(@RequestBody User user) {

        boolean success = userService.update(user);
        return Ret.success("", success);
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return Ret
     */
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('usersphere:user:del')")
    public Ret removeById(@RequestParam Long id) {
        return Ret.success("", userService.removeById(id));
    }

    /**
     * 获取角色列表
     *
     * @return Ret
     */
    @GetMapping("/getAllRole")
    public Ret getAllRole() {
        List<Role> roleList = roleService.getRoleList();
        return Ret.success("", roleList);
    }

    /**
     * 授予角色
     *
     * @return Ret
     */
    @PostMapping("/authz")
    @PreAuthorize("@pms.hasPermission('usersphere:user:authz')")
    public Ret authz(@RequestParam Long userId,@RequestBody List<Long> roleIds) {
        userService.authz(userId,roleIds);
        return Ret.success();
    }

}
