package com.hzlx.common;

import lombok.Getter;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title ResultEnum
 * @description <TODO description class purpose>
 * @createTime 2023/8/17 11:42
 **/
@Getter
public enum ResultEnum {

    SUCCESS(200,"success"),
    UNAUTHORIZED(401,"认证失败"),
    FORBIDDEN(403,"权限不足"),
    LOGIN_ERROR(100100,"用户名或密码错误"),
    PARAM_EXECTION(100202, "参数异常,请核对参数"),
    REGISTER_FAIL(100203, "系统错误，请稍后重试"),
    USER_NAME_ALREADY_EXISTS(100204, "用户名已存在");
    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }
}