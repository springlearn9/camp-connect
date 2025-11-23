package com.ls.campusconnect.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FoundItemRequest {
    @NotBlank(message = "Item name is required")
    private String itemName;
    
    private String description;
    
    private String location;
    
    private LocalDateTime foundDate;
    
    private String status; // AVAILABLE, CLAIMED, VERIFIED, EXPIRED
    
    private String category; // ELECTRONICS, DOCUMENTS, CLOTHING, ACCESSORIES, BOOKS, OTHER
    
    private String contactInfo;
    
    private String photoUrl;
    
    private String additionalImages; // JSON array of image URLs
    
    private String distinctiveFeatures;
    
    private String handoverLocation;
    
    private Boolean verificationRequired;
    
    private Boolean isAnonymous;
    
    // Optional - defaults to Campus Administrator (ID 11) if not provided or invalid
    private Long reportedById;
}
