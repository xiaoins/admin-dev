package com.xiaoins.admin.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.user.dto.UserCreateDTO;
import com.xiaoins.admin.user.dto.UserPasswordDTO;
import com.xiaoins.admin.user.dto.UserQueryDTO;
import com.xiaoins.admin.user.dto.UserUpdateDTO;
import com.xiaoins.admin.user.vo.UserVO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 分页查询用户列表
     *
     * @param query 查询条件
     * @return 用户分页列表
     */
    Page<UserVO> getUserPage(UserQueryDTO query);

    /**
     * 根据ID获取用户详情
     *
     * @param userId 用户ID
     * @return 用户详情
     */
    UserVO getUserById(Long userId);

    /**
     * 创建用户
     *
     * @param dto 创建用户DTO
     * @return 用户ID
     */
    Long createUser(UserCreateDTO dto);

    /**
     * 更新用户
     *
     * @param dto 更新用户DTO
     * @return 是否成功
     */
    Boolean updateUser(UserUpdateDTO dto);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean deleteUser(Long userId);

    /**
     * 启用/禁用用户
     *
     * @param userId 用户ID
     * @param status 状态（0禁用 1启用）
     * @return 是否成功
     */
    Boolean updateUserStatus(Long userId, Integer status);

    /**
     * 重置用户密码
     *
     * @param dto 密码DTO
     * @return 是否成功
     */
    Boolean resetPassword(UserPasswordDTO dto);
}

