package com.veryreader.d2p.api.modules.permission.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.veryreader.d2p.api.modules.permission.entity.Resource;
import com.veryreader.d2p.api.modules.permission.mapper.ResourceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private List<Resource> buildTree(List<Resource> all, Resource parent ){
        long parentId = 0L;
        if(parent!=null){
            parentId = parent.getId();
        }
        List<Resource> children = new ArrayList<>();
        for (Resource resource : all) {
            if(resource.getParentId() == null){
                resource.setParentId(0L);
            }
            if(resource.getParentId() == parentId){
                children.add(resource);
            }
        }
        if(parent!= null){
            parent.setChildren(children);
        }
        for (Resource child : children) {
            buildTree(all,child);
        }
        return children;
    }

    @Override
    public List<Resource> findResourceTree(Resource query) {
        LambdaQueryWrapper<Resource> wrapper = Wrappers.lambdaQuery(query);
        wrapper.orderByAsc(Resource::getSort);
        List<Resource> list = this.list(wrapper);
        return buildTree(list,null);
    }

    @Override
    public List<Resource> findResourceTreeByRoleIds(List<Long> roleIds) {
        if(roleIds == null || roleIds.size() == 0){
            return new ArrayList<>(0);
        }
        List<Resource> list = baseMapper.selectByRoleIds(roleIds);
        return buildTree(list,null);
    }

    @Override
    public void clearPermissionCache() {

    }


    @Override
    public List<String> findPermissionByRoleIds(List<Long> roleIds) {
        if(roleIds == null || roleIds.size() == 0){
            return new ArrayList<>(0);
        }
        List<Resource> list = baseMapper.selectByRoleIds(roleIds);
        List<String> permissions = new ArrayList<>();
        for (Resource item : list) {
            if(StringUtils.isNotBlank(item.getPermission())){
                permissions.add(item.getPermission());
            }
        }
        return permissions;
    }
}
