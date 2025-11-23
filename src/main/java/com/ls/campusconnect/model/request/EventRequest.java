package com.ls.campusconnect.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventRequest {
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    @NotNull(message = "Event date is required")
    private LocalDateTime eventDate;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    private String imageUrl;
    
    private String status; // ACTIVE, CANCELLED, COMPLETED
    
    // Optional - defaults to Campus Administrator (ID 11) if not provided or invalid
    private Long postedById;
}
