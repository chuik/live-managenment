package com.hzlx.handler;

import com.alibaba.fastjson.JSON;
import com.hzlx.common.ResponseResult;
import com.hzlx.common.ResultEnum;
import com.hzlx.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title AccessDeniedHandlerImpl
 * @description 拒绝访问处理器
 * @createTime 2023/8/17 11:35
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = ResponseResult.error(ResultEnum.FORBIDDEN);
        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(response, jsonString);
    }
}
