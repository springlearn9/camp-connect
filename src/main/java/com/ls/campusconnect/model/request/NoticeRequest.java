package com.ls.campusconnect.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NoticeRequest {
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    private String priority; // HIGH, NORMAL, LOW
    
    private String category; // ACADEMIC, ADMINISTRATIVE, EVENT, GENERAL
    
    private LocalDateTime validUntil;
    
    private String status; // ACTIVE, ARCHIVED, DRAFT
    
    private String attachmentUrl;
    
    // Optional - defaults to Campus Administrator (ID 11) if not provided or invalid
    private Long postedById;
}
