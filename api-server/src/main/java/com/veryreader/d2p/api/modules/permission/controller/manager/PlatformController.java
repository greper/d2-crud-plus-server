package com.veryreader.d2p.api.modules.permission.controller.manager;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.permission.entity.Platform;
import com.veryreader.d2p.api.modules.permission.service.PlatformService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/permission/manager/platform")
public class PlatformController {
    private final PlatformService platformService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @GetMapping("/page")
    public Ret getPlatformPage(Page<Platform> page, Platform query
            , @RequestParam(value = "dateRange", required = false) String dateRange
    ) {

        // setDefaultSort(page, "create_time", true);

        LambdaQueryWrapper<Platform> wrapper = Wrappers.lambdaQuery(query);
        // betweenDateRange(wrapper, Platform::getCreateTime, dateRange);
        // wrapper.eq(Platform::getDelFlag, '0');


        return Ret.success("", platformService.page(page, wrapper));
    }

    /**
     * @return Ret
     */
    @GetMapping("/list")
    public Ret list() {
        return Ret.success("", platformService.list());
    }

    /**
     * 通过id查询
     *
     * @param id id
     * @return Ret
     */
    @GetMapping("/info")
    public Ret getById(@RequestParam("id") Long id) {
        return Ret.success("", platformService.getById(id));
    }

    /**
     * 新增
     *
     * @param platform
     * @return Ret
     */
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('permission:platform:add')")
    public Ret save(@RequestBody Platform platform) {
        platformService.save(platform);
        return Ret.success("", platform.getId());
    }

    /**
     * 修改
     *
     * @param platform
     * @return Ret
     */
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('permission:platform:edit')")
    public Ret updateById(@RequestBody Platform platform) {
        platform.setCode(null); //不允许修改code
        return Ret.success("", platformService.updateById(platform));
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return Ret
     */
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('permission:platform:del')")
    public Ret removeById(@RequestParam Long id) {
        return Ret.success("", platformService.removeById(id));
    }

}




