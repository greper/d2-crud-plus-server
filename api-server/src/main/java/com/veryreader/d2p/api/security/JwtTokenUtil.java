package com.veryreader.d2p.api.security;

import com.veryreader.d2p.api.security.config.SecurityPropertiesConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Optional;

/**
 * <p>JwtTokenUtil</p>
 */
public class JwtTokenUtil {


    /**
     * 过期时间是 1800 秒
     */
    private static final long EXPIRATION = 1800L;

    public static String createToken(SecurityPropertiesConfig config, Claims claims) {
        return createToken(null, config.getJwtSecret(), config.getJwtIssuer(), config.getTokenExpiration(),claims);
    }

    /**
     * 创建 token
     *
     * @param issuer     签发人
     * @param subject    主体,即用户信息的JSON
     * @param expiration 有效时间(秒)
     * @param claims     自定义参数
     * @return
     */
    public static String createToken(String subject,String secret,String issuer,Integer expiration, Claims claims) {
        return Jwts.builder()
                // JWT_ID：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
//                .setId(id)
                // 签名算法以及密匙
                .signWith(SignatureAlgorithm.HS512, secret)
                // 自定义属性
                .setClaims(claims)
                // 主题：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                // 受众
//                .setAudience(loginName)
                // 签发人
                .setIssuer(issuer)
                // 签发时间
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + (expiration) * 1000))
                .compact();
    }


    /**
     * 校验是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token,String secret) {
        return getTokenBody(token,secret).getExpiration().before(new Date());
    }

    /**
     * 获得 token 的 body
     *
     * @param token
     * @return
     */
    public static Claims getTokenBody(String token,String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}