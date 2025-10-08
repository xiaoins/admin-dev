package com.xiaoins.admin.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.common.exception.BusinessException;
import com.xiaoins.admin.common.result.ResultCode;
import com.xiaoins.admin.user.dto.UserCreateDTO;
import com.xiaoins.admin.user.dto.UserPasswordDTO;
import com.xiaoins.admin.user.dto.UserQueryDTO;
import com.xiaoins.admin.user.dto.UserUpdateDTO;
import com.xiaoins.admin.user.entity.SysRole;
import com.xiaoins.admin.user.entity.SysUser;
import com.xiaoins.admin.user.mapper.SysRoleMapper;
import com.xiaoins.admin.user.mapper.SysUserMapper;
import com.xiaoins.admin.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SysUserMapper userMapper;
    private final SysRoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserVO> getUserPage(UserQueryDTO query) {
        // 构建查询条件
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(query.getUsername()), SysUser::getUsername, query.getUsername())
                .like(StringUtils.hasText(query.getRealName()), SysUser::getRealName, query.getRealName())
                .eq(StringUtils.hasText(query.getPhone()), SysUser::getPhone, query.getPhone())
                .eq(query.getRoleId() != null, SysUser::getRoleId, query.getRoleId())
                .eq(query.getStatus() != null, SysUser::getStatus, query.getStatus())
                .orderByDesc(SysUser::getCreateTime);

        // 分页查询
        Page<SysUser> page = new Page<>(query.getPageNum(), query.getPageSize());
        userMapper.selectPage(page, wrapper);

        // 转换为VO
        Page<UserVO> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        voPage.setRecords(page.getRecords().stream().map(this::convertToVO).toList());

        return voPage;
    }

    @Override
    public UserVO getUserById(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }
        return convertToVO(user);
    }

    @Override
    public Long createUser(UserCreateDTO dto) {
        // 检查用户名是否已存在
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, dto.getUsername());
        if (userMapper.selectCount(wrapper) > 0) {
            throw new BusinessException("用户名已存在");
        }

        // 检查手机号是否已存在
        if (StringUtils.hasText(dto.getPhone())) {
            wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysUser::getPhone, dto.getPhone());
            if (userMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("手机号已被使用");
            }
        }

        // 检查角色是否存在
        SysRole role = roleMapper.selectById(dto.getRoleId());
        if (role == null) {
            throw new BusinessException("角色不存在");
        }

        // 创建用户
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        // 加密密码
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userMapper.insert(user);

        log.info("创建用户成功: userId={}, username={}", user.getUserId(), user.getUsername());
        return user.getUserId();
    }

    @Override
    public Boolean updateUser(UserUpdateDTO dto) {
        // 检查用户是否存在
        SysUser existUser = userMapper.selectById(dto.getUserId());
        if (existUser == null) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }

        // 检查手机号是否被其他用户使用
        if (StringUtils.hasText(dto.getPhone())) {
            LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysUser::getPhone, dto.getPhone())
                    .ne(SysUser::getUserId, dto.getUserId());
            if (userMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("手机号已被其他用户使用");
            }
        }

        // 检查角色是否存在
        SysRole role = roleMapper.selectById(dto.getRoleId());
        if (role == null) {
            throw new BusinessException("角色不存在");
        }

        // 更新用户
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        int rows = userMapper.updateById(user);

        log.info("更新用户成功: userId={}", dto.getUserId());
        return rows > 0;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        // 检查用户是否存在
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }

        // 不能删除admin用户
        if ("admin".equals(user.getUsername())) {
            throw new BusinessException("不能删除系统管理员");
        }

        int rows = userMapper.deleteById(userId);
        log.info("删除用户成功: userId={}, username={}", userId, user.getUsername());
        return rows > 0;
    }

    @Override
    public Boolean updateUserStatus(Long userId, Integer status) {
        // 检查用户是否存在
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }

        // 不能禁用admin用户
        if ("admin".equals(user.getUsername()) && status == 0) {
            throw new BusinessException("不能禁用系统管理员");
        }

        user.setStatus(status);
        int rows = userMapper.updateById(user);

        log.info("更新用户状态成功: userId={}, status={}", userId, status);
        return rows > 0;
    }

    @Override
    public Boolean resetPassword(UserPasswordDTO dto) {
        // 检查用户是否存在
        SysUser user = userMapper.selectById(dto.getUserId());
        if (user == null) {
            throw new BusinessException(ResultCode.DATA_NOT_FOUND);
        }

        // 加密新密码
        String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(encodedPassword);
        int rows = userMapper.updateById(user);

        log.info("重置密码成功: userId={}, username={}", dto.getUserId(), user.getUsername());
        return rows > 0;
    }

    /**
     * 转换为VO
     */
    private UserVO convertToVO(SysUser user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);

        // 查询角色名称
        if (user.getRoleId() != null) {
            SysRole role = roleMapper.selectById(user.getRoleId());
            if (role != null) {
                vo.setRoleName(role.getRoleName());
            }
        }

        return vo;
    }
}

