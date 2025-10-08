package com.xiaoins.admin.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 更新用户DTO
 */
@Data
@Schema(description = "更新用户请求")
public class UserUpdateDTO {

    @Schema(description = "用户ID", example = "1")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "真实姓名", example = "张三")
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @Schema(description = "手机号", example = "13800138000")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Schema(description = "邮箱", example = "zhangsan@example.com")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "头像URL")
    private String avatarUrl;

    @Schema(description = "角色ID", example = "2")
    @NotNull(message = "角色不能为空")
    private Long roleId;

    @Schema(description = "状态（0禁用 1启用）", example = "1")
    private Integer status;
}

