package com.ls.campusconnect.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoticeResponse(
        Long id,
        String title,
        String description,
        String priority,
        String category,
        LocalDateTime validUntil,
        String status,
        String attachmentUrl,
        Long postedById,
        String postedByName,
        LocalDateTime createdTimestamp,
        LocalDateTime updatedTimestamp
) {
    // Method to get formatted valid until date
    public String getFormattedValidUntil() {
        if (validUntil != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
            return validUntil.format(formatter);
        }
        return "No expiry";
    }
    
    // Method to check if notice is still valid
    public boolean isValid() {
        return validUntil == null || validUntil.isAfter(LocalDateTime.now());
    }
    
    // Method to get priority badge style
    public String getPriorityBadge() {
        return switch (priority != null ? priority.toUpperCase() : "NORMAL") {
            case "HIGH" -> "badge-danger";
            case "LOW" -> "badge-info";
            default -> "badge-warning";
        };
    }
}
