package com.ls.campusconnect.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LostItemRequest {
    @NotBlank(message = "Item name is required")
    private String itemName;
    
    private String description;
    
    private String location;
    
    private LocalDateTime lostDate;
    
    private String status; // PENDING, SEARCHING, FOUND, CLAIMED, CLOSED
    
    private String category; // ELECTRONICS, DOCUMENTS, CLOTHING, ACCESSORIES, BOOKS, OTHER
    
    private Double rewardAmount;
    
    private String contactInfo;
    
    private String imageUrl;
    
    private String additionalImages; // JSON array of image URLs
    
    private Boolean urgent;
    
    private Boolean isAnonymous;
    
    // Optional - defaults to Campus Administrator (ID 11) if not provided or invalid
    private Long userId;
}
