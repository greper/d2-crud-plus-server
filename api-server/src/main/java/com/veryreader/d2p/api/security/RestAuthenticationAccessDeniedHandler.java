package com.veryreader.d2p.api.security;

import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.utils.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: JoeTao
 * createAt: 2018/9/21
 */
@Component("RestAuthenticationAccessDeniedHandler")
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //登陆状态下，权限不足执行该方法
        response.setStatus(403);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String body = JsonUtils.toJson(Ret.error(403, e.getMessage(),null));
        response.getWriter().write(body);
    }
}
