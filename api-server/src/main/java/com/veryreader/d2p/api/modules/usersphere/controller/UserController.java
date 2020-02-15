package com.veryreader.d2p.api.modules.usersphere.controller;


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
@RequestMapping("/usersphere/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    @GetMapping("/page")
    public Ret getUserPage(Page<User> page, User query
            , @RequestParam(value = "dateRange", required = false) String dateRange
    ) {

        // setDefaultSort(page, "create_time", true);

        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(query);
        // betweenDateRange(wrapper, User::getCreateTime, dateRange);
        // wrapper.eq(User::getDelFlag, '0');


        return Ret.success("",userService.page(page, wrapper));
    }

    /**
     * 通过id查询
     * @param id id
     * @return Ret
     */
    @GetMapping("/info")
    public Ret getById(@RequestParam("id") Long id) {
        return Ret.success("",userService.getById(id));
    }

    /**
     * 新增
     * @param user
     * @return Ret
     */
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('usersphere:user:add')")
    public Ret save(@RequestBody User user) {
        userService.save(user);
        return Ret.success("",user.getId());
    }

    /**
     * 修改
     * @param user
     * @return Ret
     */
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('usersphere:user:edit')")
    public Ret updateById(@RequestBody User user) {
        return Ret.success("",userService.updateById(user));
    }

    /**
     * 通过id删除
     * @param id id
     * @return Ret
     */
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('usersphere:user:del')")
    public Ret removeById(@RequestParam Long id) {
        return Ret.success("",userService.removeById(id));
    }


}
