package com.ls.campusconnect.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LostItemResponse(
        Long id,
        String itemName,
        String description,
        String location,
        LocalDateTime lostDate,
        String status,
        String category,
        Double rewardAmount,
        String contactInfo,
        String imageUrl,
        String additionalImages,
        Boolean urgent,
        Boolean isAnonymous,
        Long userId,
        String userName,
        LocalDateTime createdTimestamp,
        LocalDateTime updatedTimestamp
) {
    // Method to get formatted lost date
    public String getFormattedLostDate() {
        if (lostDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            return lostDate.format(formatter);
        }
        return null;
    }
    
    // Method to get formatted reward amount
    public String getFormattedRewardAmount() {
        if (rewardAmount != null && rewardAmount > 0) {
            return String.format("₹%.2f", rewardAmount);
        }
        return "No reward";
    }
    
    // Method to check if item is actively being searched
    public boolean isActivelySearching() {
        return "SEARCHING".equalsIgnoreCase(status) || "PENDING".equalsIgnoreCase(status);
    }
}
