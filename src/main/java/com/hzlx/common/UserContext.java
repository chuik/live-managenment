package com.hzlx.common;

import com.hzlx.entity.SysUser;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title UserContext
 * @description <TODO description class purpose>
 * @createTime 2023/8/17 11:13
 **/
public class UserContext {
    private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<>();

    public static void setUserHolder(SysUser sysUser) {
        userHolder.set(sysUser);
    }
    public static SysUser getUserHolder() {
        return userHolder.get();
    }


}
