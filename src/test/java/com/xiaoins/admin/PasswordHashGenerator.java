package com.xiaoins.admin;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    
    @Test
    public void generatePasswordHash() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123";
        String hash = encoder.encode(password);
        
        System.out.println("==================================================");
        System.out.println("生成的BCrypt哈希:");
        System.out.println(hash);
        System.out.println("哈希长度: " + hash.length());
        System.out.println("==================================================");
        System.out.println("\nSQL更新语句:");
        System.out.println("UPDATE sys_user SET password = '" + hash + "' WHERE username IN ('admin', 'operator', 'analyst');");
        System.out.println("==================================================");
        
        // 验证
        boolean matches = encoder.matches(password, hash);
        System.out.println("\n验证结果: " + (matches ? "✓ 成功" : "✗ 失败"));
    }
}

