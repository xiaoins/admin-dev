package com.xiaoins.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 电商后台管理系统启动类
 * 
 * @author 苏垒
 * @version 1.0
 */
@EnableCaching
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("""
            
            ========================================
            电商后台管理系统启动成功！
            API文档地址: http://localhost:8080/api/admin/v1/doc.html
            ========================================
            """);
    }
}
