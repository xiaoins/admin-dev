package com.xiaoins.admin.common.upload;

import com.xiaoins.admin.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传控制器
 */
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "文件上传", description = "文件上传相关接口")
public class FileController {

    private final FileUploadService fileUploadService;

    /**
     * 上传单个文件
     */
    @PostMapping("/single")
    @Operation(summary = "上传单个文件", description = "支持头像、商品图片等单个文件上传")
    public Result<Map<String, String>> uploadSingle(
            @Parameter(description = "上传的文件") @RequestParam("file") MultipartFile file,
            @Parameter(description = "文件类型（avatar/product）") @RequestParam(value = "type", defaultValue = "other") String type) {
        try {
            String fileUrl = fileUploadService.uploadFile(file, type);

            Map<String, String> result = new HashMap<>();
            result.put("url", fileUrl);
            result.put("name", file.getOriginalFilename());

            return Result.success(result);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("500", "文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 批量上传文件
     */
    @PostMapping("/batch")
    @Operation(summary = "批量上传文件", description = "支持商品相册等多个文件上传")
    public Result<List<Map<String, String>>> uploadBatch(
            @Parameter(description = "上传的文件列表") @RequestParam("files") MultipartFile[] files,
            @Parameter(description = "文件类型（avatar/product）") @RequestParam(value = "type", defaultValue = "other") String type) {
        try {
            List<Map<String, String>> results = new ArrayList<>();

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileUrl = fileUploadService.uploadFile(file, type);

                    Map<String, String> result = new HashMap<>();
                    result.put("url", fileUrl);
                    result.put("name", file.getOriginalFilename());
                    results.add(result);
                }
            }

            return Result.success(results);
        } catch (Exception e) {
            log.error("批量文件上传失败", e);
            return Result.error("500", "批量文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 删除文件
     */
    @DeleteMapping
    @Operation(summary = "删除文件", description = "根据文件URL删除文件")
    public Result<Void> deleteFile(@Parameter(description = "文件URL") @RequestParam("url") String fileUrl) {
        try {
            fileUploadService.deleteFile(fileUrl);
            return Result.success();
        } catch (Exception e) {
            log.error("文件删除失败", e);
            return Result.error("500", "文件删除失败：" + e.getMessage());
        }
    }
}

