package com.ls.common.service;

import com.ls.common.config.FileStorageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private final FileStorageProperties fileStorageProperties;
    private Path fileStorageLocation;

    /**
     * Initializes the file storage location
     */
    public void init() {
        try {
            this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                    .toAbsolutePath().normalize();
            Files.createDirectories(this.fileStorageLocation);
            log.info("File storage location initialized: {}", this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create upload directory!", ex);
        }
    }

    /**
     * Stores a file and returns the file path
     * 
     * @param file the multipart file to store
     * @return the stored file path
     */
    public String storeFile(MultipartFile file) {
        // Initialize storage if not already done
        if (this.fileStorageLocation == null) {
            init();
        }

        // Validate file
        validateFile(file);

        // Generate unique filename
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = getFileExtension(originalFilename);
        String fileName = UUID.randomUUID().toString() + "." + fileExtension;

        try {
            // Check for invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Invalid file path: " + fileName);
            }

            // Copy file to target location
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            log.info("File stored successfully: {}", fileName);
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName, ex);
        }
    }

    /**
     * Stores multiple files and returns their paths as a comma-separated string
     * 
     * @param files array of multipart files
     * @return comma-separated file paths
     */
    public String storeFiles(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return null;
        }

        StringBuilder filePaths = new StringBuilder();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isEmpty()) {
                String filePath = storeFile(files[i]);
                filePaths.append(filePath);
                if (i < files.length - 1) {
                    filePaths.append(",");
                }
            }
        }
        return filePaths.toString();
    }

    /**
     * Loads a file from storage
     * 
     * @param fileName the file name
     * @return the file path
     */
    public Path loadFile(String fileName) {
        if (this.fileStorageLocation == null) {
            init();
        }
        return this.fileStorageLocation.resolve(fileName).normalize();
    }

    /**
     * Deletes a file from storage
     * 
     * @param fileName the file name
     * @return true if deleted successfully
     */
    public boolean deleteFile(String fileName) {
        try {
            Path filePath = loadFile(fileName);
            return Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            log.error("Could not delete file: {}", fileName, ex);
            return false;
        }
    }

    /**
     * Validates file size and extension
     */
    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Cannot upload empty file");
        }

        // Check file size
        if (file.getSize() > fileStorageProperties.getMaxFileSize()) {
            throw new RuntimeException("File size exceeds maximum allowed size");
        }

        // Check file extension
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);
        
        if (!isAllowedExtension(fileExtension)) {
            throw new RuntimeException("File type not allowed. Allowed types: " + 
                    Arrays.toString(fileStorageProperties.getAllowedExtensions()));
        }
    }

    /**
     * Gets file extension from filename
     */
    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        return lastDotIndex > 0 ? fileName.substring(lastDotIndex + 1).toLowerCase() : "";
    }

    /**
     * Checks if file extension is allowed
     */
    private boolean isAllowedExtension(String extension) {
        return Arrays.asList(fileStorageProperties.getAllowedExtensions())
                .contains(extension.toLowerCase());
    }
}
