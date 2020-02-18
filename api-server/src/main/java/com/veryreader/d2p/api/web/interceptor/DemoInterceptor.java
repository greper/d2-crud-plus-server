package com.veryreader.d2p.api.web.interceptor;

import com.veryreader.d2p.api.config.DemoConfig;
import com.veryreader.d2p.api.exceptions.ClientException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: xiaojunnuo
 */
@Component
@AllArgsConstructor
public class DemoInterceptor extends HandlerInterceptorAdapter {

    private final DemoConfig config;

    private static AntPathMatcher ANT_PATHMATCHER = new AntPathMatcher();
    private static String[] INTERCEPT_URLS = new String[]{
            "/*/manager/*/add",
            "/*/manager/*/update",
            "/*/manager/*/delete",
            "/*/manager/*/authz"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!config.getEditDisabled()){
            return true;
        }
        String path = request.getRequestURI();
        for (String item : INTERCEPT_URLS) {
            if (ANT_PATHMATCHER.match(request.getContextPath() + item, path)) {
                throw new ClientException("DEMO环境不允许修改数据，敬请谅解");
            }
        }

        return true;
    }
}
