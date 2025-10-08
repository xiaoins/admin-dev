package com.xiaoins.admin.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoins.admin.auth.dto.LoginRequest;
import com.xiaoins.admin.auth.vo.LoginResponse;
import com.xiaoins.admin.common.exception.BusinessException;
import com.xiaoins.admin.common.result.ResultCode;
import com.xiaoins.admin.common.util.JwtUtil;
import com.xiaoins.admin.user.entity.SysUser;
import com.xiaoins.admin.user.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 登录
     */
    public LoginResponse login(LoginRequest request) {
        // 查询用户
        SysUser user = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, request.getUsername())
        );

        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }

        // 验证用户状态
        if (user.getStatus() == 0) {
            throw new BusinessException(ResultCode.USER_DISABLED);
        }

        // 验证密码
        log.info("========== 密码验证调试信息 ==========");
        log.info("用户名: {}", request.getUsername());
        log.info("输入的密码: {}", request.getPassword());
        log.info("数据库密码哈希: {}", user.getPassword());
        log.info("密码哈希长度: {}", user.getPassword() != null ? user.getPassword().length() : 0);
        
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        log.info("密码验证结果: {}", matches);
        log.info("====================================");
        
        if (!matches) {
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }

        // 生成Token
        String token = jwtUtil.generateToken(user.getUserId(), user.getUsername());

        // 构造响应
        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresIn(expiration / 1000)
                .userInfo(LoginResponse.UserInfo.builder()
                        .userId(user.getUserId())
                        .username(user.getUsername())
                        .realName(user.getRealName())
                        .avatarUrl(user.getAvatarUrl())
                        .roleId(user.getRoleId())
                        .build())
                .build();
    }

    /**
     * 登出
     */
    public void logout() {
        // 可以在这里将Token加入黑名单
        log.info("用户登出");
    }
}

