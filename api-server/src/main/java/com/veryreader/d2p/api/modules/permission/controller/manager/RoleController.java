package com.veryreader.d2p.api.modules.permission.controller.manager;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.base.controller.AbstractCrudController;
import com.veryreader.d2p.api.modules.permission.entity.Role;
import com.veryreader.d2p.api.modules.permission.service.RoleResourceService;
import com.veryreader.d2p.api.modules.permission.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/permission/manager/role")
public class RoleController  extends AbstractCrudController {
    private final RoleService roleService;
    private final RoleResourceService roleResourceService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @GetMapping("/page")
    public Ret getRolePage(Page page, Role query
            , @RequestParam(value = "dateRange", required = false) String dateRange
    ) {

        setDefaultSort(page, "id", false);
        // setDefaultSort(page, "create_time", true); //实际项目中可以配置为按添加时间倒序排序

        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery(query);

        return Ret.success("", roleService.page(page, wrapper));
    }

    /**
     * 通过id查询
     *
     * @param id id
     * @return Ret
     */
    @GetMapping("/info")
    public Ret getById(@RequestParam("id") Long id) {
        return Ret.success("", roleService.getById(id));
    }

    /**
     * 新增
     *
     * @param role
     * @return Ret
     */
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('permission:role:add')")
    public Ret save(@RequestBody Role role) {
        roleService.save(role);
        return Ret.success("", role.getId());
    }

    /**
     * 修改
     *
     * @param role
     * @return Ret
     */
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('permission:role:edit')")
    public Ret updateById(@RequestBody Role role) {
        return Ret.success("", roleService.updateById(role));
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return Ret
     */
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('permission:role:del')")
    public Ret removeById(@RequestParam Long id) {
        return Ret.success("", roleService.removeById(id));
    }

    /**
     * 给角色授予权限
     * @return Ret
     */
    @PostMapping("/authz")
    @PreAuthorize("@pms.hasPermission('permission:role:authz')")
    public Ret authz(@RequestParam Long roleId,@RequestBody List<Long> resourceIds) {
        roleService.authz(roleId,resourceIds);
        return Ret.success("", null);
    }

    /**
     * 获取权限
     * @return Ret
     */
    @GetMapping("/getPermission")
    @PreAuthorize("@pms.hasPermission('permission:role:authz')")
    public Ret getPermission(@RequestParam Long roleId) {
        List<Long> ids = roleResourceService.getResourceIdsByRoleId(roleId);
        return Ret.success("", ids);
    }

    /**
     * 获取角色列表
     *
     * @return Ret
     */
    @GetMapping("/list")
    public Ret list() {
        List<Role> roleList = roleService.getRoleList();
        return Ret.success("", roleList);
    }


}




