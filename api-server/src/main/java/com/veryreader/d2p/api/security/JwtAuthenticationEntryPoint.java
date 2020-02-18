package com.veryreader.d2p.api.security;

import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author: JoeTao
 * createAt: 2018/9/20
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        //验证为未登陆状态会进入此方法，认证错误
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        String body = JsonUtils.toJson(Ret.error(401, authException.getMessage(),null));
        printWriter.write(body);
        printWriter.flush();
    }
}
