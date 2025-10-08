package com.xiaoins.admin.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoins.admin.common.exception.BusinessException;
import com.xiaoins.admin.common.result.ResultCode;
import com.xiaoins.admin.user.entity.SysUser;
import com.xiaoins.admin.user.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 用户详情服务实现
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, username)
        );

        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        if (sysUser.getStatus() == 0) {
            throw new BusinessException(ResultCode.USER_DISABLED);
        }

        // 这里简化处理，实际应该查询用户的角色和权限
        return User.builder()
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }
}

