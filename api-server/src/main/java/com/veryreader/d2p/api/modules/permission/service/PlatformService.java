package com.veryreader.d2p.api.modules.permission.service;

import com.veryreader.d2p.api.modules.permission.entity.Platform;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-28
 */
public interface PlatformService extends IService<Platform> {

    Platform getByCode(String platformCode);
}
