package com.hzlx.mapper;

import com.hzlx.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 马则帝
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2023-08-17 09:24:37
* @Entity com.hzlx.entity.SysUser
*/
//@Mapper()
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户ID 获取用户权限标识集合
     * @param UserId 用户ID
     * @return java.util.List<String>
     */
    List<String> getUsersPermissionsById(Long UserId);
}




