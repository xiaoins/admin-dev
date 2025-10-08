package com.xiaoins.admin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件上传配置
 */
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    @Value("${file.upload.path:uploads}")
    private String uploadPath;

    /**
     * 配置静态资源访问路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置文件访问路径映射
        // 访问路径：/api/admin/v1/files/**
        // 实际路径：file:uploads/
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
}

