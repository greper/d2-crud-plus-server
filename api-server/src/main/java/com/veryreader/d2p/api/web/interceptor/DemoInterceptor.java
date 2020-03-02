package com.veryreader.d2p.api.web.interceptor;

import com.veryreader.d2p.api.config.DemoConfig;
import com.veryreader.d2p.api.exceptions.ClientException;
import com.veryreader.d2p.api.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Author: xiaojunnuo
 */
@Component
@AllArgsConstructor
@Slf4j
public class DemoInterceptor extends HandlerInterceptorAdapter {

    private final DemoConfig config;

    private static AntPathMatcher ANT_PATHMATCHER = new AntPathMatcher();
    private static String[] INTERCEPT_URLS = new String[]{
            // "/*/manager/*/add",
            "/*/manager/*/update**",
            "/*/manager/*/delete**",
            "/*/manager/*/authz**"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!config.getEditDisabled()) {
            return true;
        }
        String path = request.getRequestURI();
        for (String item : INTERCEPT_URLS) {
            if (!ANT_PATHMATCHER.match(request.getContextPath() + item, path)) {
                continue;
            }

            Long id = null;
            if (item.contains("/*/update")) {
                try {
                    RequestWrapper myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
                    String body = myRequestWrapper.getBody();
                    if (StringUtils.isNotBlank(body)) {
                        Map<String, Object> map = JsonUtils.toBean(body);
                        Object idObj = map.get("id");
                        if (idObj != null) {
                            id = Long.parseLong(idObj.toString());
                        }

                    }
                } catch (Exception e) {
                    log.error("读取body数据出错", e);
                }
            }

            if (id == null) {
                id = getLongId(request);
            }
            int minCanEdit = 1000;
            if (id != null && id < minCanEdit) {
                throw new ClientException("为保证系统功能正常，id小于" + minCanEdit + "的数据不允许修改，如需体验，请自行添加新数据");
            }
            return true;
        }

        return true;
    }

    private Long getLongId(HttpServletRequest request) {
        Long id = getLongParameter(request, "id");
        if (id == null) {
            id = getLongParameter(request, "roleId");
        }
        if (id == null) {
            id = getLongParameter(request, "userId");
        }
        return id;

    }

    private Long getLongParameter(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        if (value != null) {
            return Long.parseLong(value);
        }
        return null;

    }
}
