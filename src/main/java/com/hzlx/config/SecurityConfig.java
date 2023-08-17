package com.hzlx.config;

import com.hzlx.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title SecurityConfig
 * @description
 * @createTime 2023/8/17 10:29
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${my-config.exclude.paths.anonymous}")
    private String anonymous;
    @Value("${my-config.exclude.paths.permit}")
    private String permit;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf()
                //禁用csrf
                .disable()
                //session 的会话管理器
                .sessionManagement()
                //指定会话创建策略，当前使用的策略为不创建会话
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 以上总结：是不使用session创建会话
                .and()
                //url请求策略
                //对请求进行授权
                .authorizeRequests()
                //
                .antMatchers(
                        anonymous.split(",")
                )
                //匿名访问:只有当用户没有登录的时候才能访问
                .anonymous()
                .antMatchers(
                        permit.split(",")
                )
                .permitAll()//不需要认证，登不登陆都可以访问
                .anyRequest()
                .authenticated()//做认证做授权
                .and()
                //把自定义的jwt过滤器添加到Security过滤器链中去，并指定在UsernamePasswordAuthenticationFilter这个过滤器之前执行
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .cors();//允许跨域
    }

    /**
     * 注入密码加密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入认证管理器
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
