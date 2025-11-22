package com.ls.campusconnect.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FoundItemResponse(
        Long id,
        String itemName,
        String description,
        String location,
        LocalDateTime foundDate,
        String status,
        String category,
        String contactInfo,
        String photoUrl,
        String additionalImages,
        String distinctiveFeatures,
        String handoverLocation,
        Boolean verificationRequired,
        Boolean isAnonymous,
        Long reportedById,
        String reportedByName,
        List<Long> claimedByIds,
        List<String> claimedByNames,
        LocalDateTime claimedAt,
        LocalDateTime createdTimestamp,
        LocalDateTime updatedTimestamp
) {
    // Method to get formatted found date
    public String getFormattedFoundDate() {
        if (foundDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            return foundDate.format(formatter);
        }
        return null;
    }
    
    // Method to check if item is available for claim
    public boolean isAvailableForClaim() {
        return "AVAILABLE".equalsIgnoreCase(status);
    }
}
