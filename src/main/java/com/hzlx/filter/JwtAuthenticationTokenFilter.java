package com.hzlx.filter;

import com.hzlx.common.LoginUser;
import com.hzlx.common.UserContext;
import com.hzlx.utils.JwtUtil;
import com.hzlx.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title JwtAuthenticationTokenFilter
 * @description <TODO description class purpose>
 * @createTime 2023/8/17 11:00
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${my-config.redis-key}")
    private String redisKey;

    @Value("${my-config.token-key}")
    private String tokenKey;

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(tokenKey);
        //根据Token去Redis中取真正的令牌
        String realToken = redisCache.getCacheObject(token);
        if (!StringUtils.hasText(realToken)){
            filterChain.doFilter(request,response);
            return;
        }
        String userId;
        //解密
        try {
            Claims claims = JwtUtil.parseJWT(realToken);
            userId= claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("令牌非法");
        }
        //根据解密初开的UserId 去Redis中获取用户数据
        LoginUser loginUser = (LoginUser) redisCache.getCacheObject(redisKey+userId);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        UserContext.setUserHolder(loginUser.getSysUser());
        filterChain.doFilter(request,response);
    }
}
