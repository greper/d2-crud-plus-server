package com.veryreader.d2p.api.security;

import cn.hutool.json.JSONUtil;
import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.security.config.SecurityPropertiesConfig;
import com.veryreader.d2p.api.security.vo.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private SecurityPropertiesConfig config;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,SecurityPropertiesConfig config) {
        super(authenticationManager);
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(config.getTokenHeader());
        if(token == null){
            token = request.getParameter(config.getTokenParam());
        }
        // 如果请求头中没有Authorization信息则直接放行了
        if (token == null) {
            chain.doFilter(request,response);
            return;
        }

        token = token.replace(config.getTokenPrefix(), "");

        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            // token校验失败
            logger.error("token 校验失败");
        }

        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims body = JwtTokenUtil.getTokenBody(token,config.getJwtSecret());
        String username = body.getSubject();
        Long id = Long.parseLong( body.get("id").toString());
        List<? extends Number> roleIds = (ArrayList<Integer>) body.get("roles");
        List<Long> roleLongIds = roleIds.stream().map(Number::longValue).collect(Collectors.toList());
        LoginUser loginUser = new LoginUser(id,username,roleLongIds);
        return new UsernamePasswordAuthenticationToken(loginUser, null,loginUser.getAuthorities());
    }
}