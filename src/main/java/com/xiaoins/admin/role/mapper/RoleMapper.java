package com.xiaoins.admin.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoins.admin.user.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色Mapper
 */
@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
}

