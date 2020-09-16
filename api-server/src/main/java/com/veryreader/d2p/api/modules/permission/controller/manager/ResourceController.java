package com.veryreader.d2p.api.modules.permission.controller.manager;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.base.controller.AbstractCrudController;
import com.veryreader.d2p.api.modules.permission.entity.Resource;
import com.veryreader.d2p.api.modules.permission.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/permission/manager/resource")
public class ResourceController extends AbstractCrudController {
    private final ResourceService resourceService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @GetMapping("/page")
    public Ret getResourcePage(Page<Resource> page, Resource query
            , @RequestParam(value = "dateRange", required = false) String dateRange
    ) {

        setDefaultSort(page, "id", false);
        // setDefaultSort(page, "create_time", true); //实际项目中可以配置为按添加时间倒序排序

        LambdaQueryWrapper<Resource> wrapper = Wrappers.lambdaQuery(query);

        return Ret.success("", resourceService.page(page, wrapper));
    }

    /**
     * 查询全部，返回树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    public Ret getTree(Resource query) {
        List<Resource> tree = resourceService.findResourceTree(query);
        return Ret.success("", tree);
    }


    /**
     * 通过id查询菜单
     *
     * @param id id
     * @return Ret
     */
    @GetMapping("/info")
    public Ret getById(@RequestParam("id") Long id) {
        return Ret.success("", resourceService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param resource 菜单
     * @return Ret
     */
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('permission:resource:add')")
    public Ret save(@RequestBody Resource resource) {
        resourceService.save(resource);
        return Ret.success("", resource.getId());
    }

    /**
     * 修改菜单
     *
     * @param resource 菜单
     * @return Ret
     */
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('permission:resource:edit')")
    public Ret updateById(@RequestBody Resource resource) {
        return Ret.success("", resourceService.updateById(resource));
    }

    /**
     * 通过id删除菜单
     *
     * @param id id
     * @return Ret
     */
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('permission:resource:del')")
    public Ret removeById(@RequestParam Long id) {
        return Ret.success("", resourceService.removeById(id));
    }

}




