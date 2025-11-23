package com.ls.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file.upload")
@Data
public class FileStorageProperties {
    private String uploadDir = "uploads";
    private long maxFileSize = 5242880; // 5MB default
    private String[] allowedExtensions = {"jpg", "jpeg", "png", "gif"};
}
