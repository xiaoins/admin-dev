package com.xiaoins.admin.common.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 文件上传服务
 */
@Service
@Slf4j
public class FileUploadService {

    @Value("${file.upload.path:uploads}")
    private String uploadPath;

    @Value("${file.upload.domain:http://localhost:8080}")
    private String domain;

    /**
     * 上传单个文件（无类型参数）
     *
     * @param file 上传的文件
     * @return 文件访问URL
     */
    public String uploadFile(MultipartFile file) throws IOException {
        return uploadFile(file, "other");
    }

    /**
     * 上传单个文件
     *
     * @param file 上传的文件
     * @param type 文件类型（avatar/product）
     * @return 文件访问URL
     */
    public String uploadFile(MultipartFile file, String type) throws IOException {
        // 验证文件
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        // 获取原始文件名和扩展名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        String extension = getFileExtension(originalFilename);
        validateFileExtension(extension);

        // 生成新文件名（UUID + 扩展名）
        String newFilename = UUID.randomUUID().toString().replace("-", "") + "." + extension;

        // 构建文件存储路径：uploadPath/type/yyyy-MM-dd/filename
        String dateFolder = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String relativePath = type + File.separator + dateFolder;
        
        // 获取绝对路径（如果uploadPath是相对路径，转为项目根目录下的绝对路径）
        Path baseUploadPath = Paths.get(uploadPath);
        if (!baseUploadPath.isAbsolute()) {
            // 获取项目根目录（pom.xml所在目录）
            // 通过获取classpath的父目录来定位项目根目录
            try {
                File classPath = ResourceUtils.getFile("classpath:");
                // classPath 是 target/classes，需要向上两级到达项目根目录
                File projectRoot = classPath.getParentFile().getParentFile();
                baseUploadPath = Paths.get(projectRoot.getAbsolutePath(), uploadPath);
                log.info("项目根目录：{}", projectRoot.getAbsolutePath());
            } catch (Exception e) {
                // 如果获取失败，使用user.dir作为后备方案
                log.warn("无法获取classpath，使用user.dir作为项目根目录");
                baseUploadPath = Paths.get(System.getProperty("user.dir"), uploadPath);
            }
        }
        
        Path uploadDir = baseUploadPath.resolve(relativePath);

        // 强制创建目录（包括所有父目录）
        try {
            Files.createDirectories(uploadDir);
            log.info("创建上传目录：{}", uploadDir.toAbsolutePath());
        } catch (IOException e) {
            log.error("创建目录失败：{}", uploadDir.toAbsolutePath(), e);
            throw new IOException("无法创建上传目录：" + e.getMessage(), e);
        }

        // 保存文件
        Path targetPath = uploadDir.resolve(newFilename);
        try {
            file.transferTo(targetPath.toFile());
        } catch (IOException e) {
            log.error("保存文件失败：{}", targetPath.toAbsolutePath(), e);
            throw new IOException("文件保存失败：" + e.getMessage(), e);
        }

        log.info("文件上传成功：{}", targetPath.toString());

        // 返回文件访问URL
        String fileUrl = domain + "/api/admin/v1/files/" + relativePath.replace(File.separator, "/") + "/" + newFilename;
        return fileUrl;
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件URL
     */
    public void deleteFile(String fileUrl) {
        try {
            // 从URL中提取相对路径
            String prefix = domain + "/api/admin/v1/files/";
            if (fileUrl.startsWith(prefix)) {
                String relativePath = fileUrl.substring(prefix.length());
                Path filePath = Paths.get(uploadPath, relativePath.replace("/", File.separator));

                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    log.info("文件删除成功：{}", filePath.toString());
                }
            }
        } catch (Exception e) {
            log.error("文件删除失败：{}", fileUrl, e);
        }
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    /**
     * 验证文件扩展名（只允许常见图片格式）
     */
    private void validateFileExtension(String extension) {
        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "webp"};
        for (String allowed : allowedExtensions) {
            if (allowed.equals(extension)) {
                return;
            }
        }
        throw new IllegalArgumentException("不支持的文件格式，仅支持：jpg、jpeg、png、gif、webp");
    }
}

