package com.ls.campusconnect.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EventResponse(
        Long id,
        String title,
        String description,
        LocalDateTime eventDate,
        String location,
        String imageUrl,
        String status,
        Long postedById,
        String postedByName,
        LocalDateTime createdTimestamp,
        LocalDateTime updatedTimestamp
) {
    // Method to get formatted event date
    public String getFormattedEventDate() {
        if (eventDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
            return eventDate.format(formatter);
        }
        return null;
    }
    
    // Method to check if event is upcoming
    public boolean isUpcoming() {
        return eventDate != null && eventDate.isAfter(LocalDateTime.now());
    }
}
