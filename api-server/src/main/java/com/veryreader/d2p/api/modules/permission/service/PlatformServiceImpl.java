package com.veryreader.d2p.api.modules.permission.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.veryreader.d2p.api.modules.permission.entity.Platform;
import com.veryreader.d2p.api.modules.permission.mapper.PlatformMapper;
import com.veryreader.d2p.api.modules.permission.service.PlatformService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-28
 */
@Service
public class PlatformServiceImpl extends ServiceImpl<PlatformMapper, Platform> implements PlatformService {

    @Override
    public Platform getByCode(String platformCode) {
        Platform condition = new Platform();
        condition.setCode(platformCode);
        return this.baseMapper.selectOne(Wrappers.query(condition));
    }
}
