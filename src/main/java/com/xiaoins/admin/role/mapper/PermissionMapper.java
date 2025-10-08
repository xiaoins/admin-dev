package com.xiaoins.admin.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoins.admin.role.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限Mapper
 */
@Mapper
public interface PermissionMapper extends BaseMapper<SysPermission> {
}

