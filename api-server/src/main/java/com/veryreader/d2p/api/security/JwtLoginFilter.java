package com.veryreader.d2p.api.security;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.veryreader.d2p.api.model.vo.Ret;
import com.veryreader.d2p.api.modules.usersphere.entity.User;
import com.veryreader.d2p.api.modules.usersphere.service.UserService;
import com.veryreader.d2p.api.security.config.SecurityPropertiesConfig;
import com.veryreader.d2p.api.security.vo.LoginRequest;
import com.veryreader.d2p.api.security.vo.LoginUser;
import com.veryreader.d2p.api.security.vo.TokenResult;
import com.veryreader.d2p.api.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 */
@Slf4j
@AllArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final long EXPIRATION = 1800L; //30分钟
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final SecurityPropertiesConfig config;
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        return super.attemptAuthentication(request, response);
        // 从输入流中获取到登录的信息
        String req = IoUtil.read(request.getInputStream(), "UTF-8");
        LoginRequest loginRequest = JsonUtils.toBean(req, LoginRequest.class);
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userService.getByUsername(username, true);
        if (user != null) {
            LoginUser loginUser = new LoginUser(user);
            try {
                return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUser, password, loginUser.getAuthorities())
                );
            }catch (BadCredentialsException e){
                throw  new BadCredentialsException("用户名或密码错误",e);
            }

        }

        throw  new BadCredentialsException("用户名或密码错误");

    }

    //
    //

    /**
     * 成功验证后调用的方法.
     * 如果验证成功，就生成token并返回
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        LoginUser loginUser = (LoginUser) authResult.getPrincipal();
        Claims claims = new DefaultClaims();
        //claims.setId(loginUser.getId().toString());
        claims.setSubject(loginUser.getUsername());
        claims.put("id",loginUser.getId());
        claims.put("roles",loginUser.getRoleIds());

        String token = JwtTokenUtil.createToken(config,claims);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader(config.getTokenHeader(), config.getTokenPrefix() + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        TokenResult tokenRet = new TokenResult(token,loginUser);
        response.getWriter().write(JsonUtils.toJson(Ret.success("登录成功",tokenRet)));
    }


    /**
     * 这是验证失败时候调用的方法
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(Ret.error(1001,failed.getMessage(),null)));
        log.error(failed.getMessage(),failed);
    }
}