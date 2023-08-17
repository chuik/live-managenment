package com.hzlx.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.hzlx.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title LoginUser
 * @description <TODO description class purpose>
 * @createTime 2023/8/17 9:18
 **/
@Data
public class LoginUser implements UserDetails {
    @JSONField(serialize = false)
    private static final long serialVersionUID = -7109236002843078048L;
    //当前用户对象
    private SysUser sysUser;
    //当前用户登录，所拥有的权限，（权限标识符，[从数据库中查询出来的权限集合{'user:insert','user:edit',....}]）
    private List<String> permissions;
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginUser(SysUser sysUser, List<String> permissions){
        this.sysUser=sysUser;
        this.permissions=permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities!=null){
            return authorities;
        }
        authorities=permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
