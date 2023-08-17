package com.hzlx.handler;

import com.alibaba.fastjson.JSON;
import com.hzlx.common.ResponseResult;
import com.hzlx.common.ResultEnum;
import com.hzlx.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title AuthenticationEntryPointImpl
 * @description <TODO description class purpose>
 * @createTime 2023/8/17 11:47
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = ResponseResult.error(ResultEnum.UNAUTHORIZED);
        String JsonString = JSON.toJSONString(result);
        WebUtils.renderString(response, JsonString);
    }
}
