package com.xiaoins.admin.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 创建用户DTO
 */
@Data
@Schema(description = "创建用户请求")
public class UserCreateDTO {

    @Schema(description = "用户名", example = "zhangsan")
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$", message = "用户名必须是4-20位字母、数字或下划线")
    private String username;

    @Schema(description = "密码", example = "123456")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^.{6,20}$", message = "密码长度必须在6-20位之间")
    private String password;

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
    private Integer status = 1;
}

