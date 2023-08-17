package com.hzlx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hzlx.common.LoginUser;
import com.hzlx.entity.SysUser;
import com.hzlx.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title UserDetailsServiceImpl
 * @description <TODO description class purpose>
 * @createTime 2023/8/17 9:49
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUserName, username)
                        .eq(SysUser::getUserType, 0)
        );
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("没有这个用户");
        }
        //查询权限
        List<String> permissions = sysUserMapper.getUsersPermissionsById(sysUser.getId());
        return new LoginUser(sysUser, permissions);
    }
}
