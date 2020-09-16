package com.veryreader.d2p.api.security;

import cn.hutool.core.util.StrUtil;
import com.veryreader.d2p.api.modules.permission.entity.Resource;
import com.veryreader.d2p.api.modules.permission.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口权限判断工具
 */
@Slf4j
@Component("pms")
public class PermissionService {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 判断接口是否有xxx:xxx权限
	 *
	 * @param permission 权限
	 * @return {boolean}
	 */
	public boolean hasPermission(String permission) {
		if (StrUtil.isBlank(permission)) {
			return false;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<Long> roleIds = authorities.stream()
				.filter(item-> StringUtils.isNotBlank(item.getAuthority()))
				.map(item -> Long.parseLong(item.getAuthority()))
				.collect(Collectors.toList());

		//TODO 此处需要加缓存
		List<String> permissions = resourceService.findPermissionByRoleIds(roleIds);

		return permissions.contains(permission);
	}
}
