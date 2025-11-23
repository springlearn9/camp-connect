package com.ls.common.controller;

import com.ls.common.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for handling file uploads and downloads.
 */
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileUploadController {

    private final FileStorageService fileStorageService;

    /**
     * Uploads a single file
     * 
     * @param file the file to upload
     * @return ResponseEntity with file information
     */
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("Uploading file: {}", file.getOriginalFilename());
        String fileName = fileStorageService.storeFile(file);
        
        Map<String, String> response = new HashMap<>();
        response.put("fileName", fileName);
        response.put("fileUrl", "/api/files/download/" + fileName);
        response.put("fileSize", String.valueOf(file.getSize()));
        
        log.info("File uploaded successfully: {}", fileName);
        return ResponseEntity.ok(response);
    }

    /**
     * Uploads multiple files
     * 
     * @param files array of files to upload
     * @return ResponseEntity with file information
     */
    @PostMapping("/upload-multiple")
    public ResponseEntity<Map<String, Object>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        log.info("Uploading {} files", files.length);
        
        Map<String, Object> response = new HashMap<>();
        StringBuilder fileNames = new StringBuilder();
        
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isEmpty()) {
                String fileName = fileStorageService.storeFile(files[i]);
                fileNames.append(fileName);
                if (i < files.length - 1) {
                    fileNames.append(",");
                }
            }
        }
        
        response.put("fileNames", fileNames.toString());
        response.put("count", files.length);
        
        log.info("Files uploaded successfully: {}", fileNames);
        return ResponseEntity.ok(response);
    }

    /**
     * Downloads a file
     * 
     * @param fileName the file name
     * @return ResponseEntity with file resource
     */
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = fileStorageService.loadFile(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                String contentType = determineContentType(fileName);
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (Exception ex) {
            log.error("Error downloading file: {}", fileName, ex);
            throw new RuntimeException("Could not download file: " + fileName, ex);
        }
    }

    /**
     * Deletes a file
     * 
     * @param fileName the file name to delete
     * @return ResponseEntity with success message
     */
    @DeleteMapping("/delete/{fileName:.+}")
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable String fileName) {
        log.info("Deleting file: {}", fileName);
        boolean deleted = fileStorageService.deleteFile(fileName);
        
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("message", "File deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "File not found or could not be deleted");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Determines content type based on file extension
     */
    private String determineContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        return switch (extension) {
            case "png" -> "image/png";
            case "jpg", "jpeg" -> "image/jpeg";
            case "gif" -> "image/gif";
            case "pdf" -> "application/pdf";
            default -> "application/octet-stream";
        };
    }
}
