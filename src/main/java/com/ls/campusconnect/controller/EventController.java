package com.ls.campusconnect.controller;

import com.ls.campusconnect.model.request.EventRequest;
import com.ls.campusconnect.model.response.EventResponse;
import com.ls.campusconnect.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

/**
 * REST controller for managing Event resources.
 * 
 * <p>This controller provides CRUD operations for campus events including
 * creation, retrieval, updates, and search functionality. Events can be
 * filtered by status, location, poster, and other criteria.</p>
 * 
 * <p><b>Security Note:</b> All endpoints should be secured with HTTPS in production
 * and implement proper authorization checks to ensure users can only
 * access/modify events they have permission for.</p>
 */
@RestController
@RequestMapping("/api/campus/events")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    
    private final EventService eventService;

    /**
     * Creates a new event.
     * 
     * @param eventRequest the event creation request
     * @return ResponseEntity with EventResponse and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventRequest eventRequest) {
        log.info("Creating new event: {}", eventRequest.getTitle());
        EventResponse response = eventService.create(eventRequest);
        log.info("Event created with ID: {}", response.id());
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Retrieves an event by its ID.
     * 
     * @param eventId the event ID
     * @return ResponseEntity with EventResponse and HTTP 200 status
     */
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> get(@PathVariable Long eventId) {
        log.info("Retrieving event with ID: {}", eventId);
        EventResponse response = eventService.get(eventId);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all events.
     * 
     * @return ResponseEntity with list of EventResponse and HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll() {
        log.info("Retrieving all events");
        List<EventResponse> responses = eventService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves upcoming events.
     * 
     * @return ResponseEntity with list of EventResponse and HTTP 200 status
     */
    @GetMapping("/upcoming")
    public ResponseEntity<List<EventResponse>> getUpcoming() {
        log.info("Retrieving upcoming events");
        List<EventResponse> responses = eventService.getUpcomingEvents();
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves events by status.
     * 
     * @param status the event status
     * @return ResponseEntity with list of EventResponse and HTTP 200 status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<EventResponse>> getByStatus(@PathVariable String status) {
        log.info("Retrieving events with status: {}", status);
        List<EventResponse> responses = eventService.getEventsByStatus(status);
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves events by posted member.
     * 
     * @param postedById the member ID who posted the events
     * @return ResponseEntity with list of EventResponse and HTTP 200 status
     */
    @GetMapping("/posted-by/{postedById}")
    public ResponseEntity<List<EventResponse>> getByPostedBy(@PathVariable Long postedById) {
        log.info("Retrieving events posted by member ID: {}", postedById);
        List<EventResponse> responses = eventService.getEventsByPostedBy(postedById);
        return ResponseEntity.ok(responses);
    }

    /**
     * Searches events by title.
     * 
     * @param title the title keyword to search
     * @return ResponseEntity with list of EventResponse and HTTP 200 status
     */
    @GetMapping("/search/title")
    public ResponseEntity<List<EventResponse>> searchByTitle(@RequestParam String title) {
        log.info("Searching events by title: {}", title);
        List<EventResponse> responses = eventService.searchEventsByTitle(title);
        return ResponseEntity.ok(responses);
    }

    /**
     * Searches events by location.
     * 
     * @param location the location keyword to search
     * @return ResponseEntity with list of EventResponse and HTTP 200 status
     */
    @GetMapping("/search/location")
    public ResponseEntity<List<EventResponse>> searchByLocation(@RequestParam String location) {
        log.info("Searching events by location: {}", location);
        List<EventResponse> responses = eventService.searchEventsByLocation(location);
        return ResponseEntity.ok(responses);
    }

    /**
     * Updates an existing event.
     * 
     * @param eventId the event ID to update
     * @param eventRequest the update request
     * @return ResponseEntity with updated EventResponse and HTTP 200 status
     */
    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> update(
            @PathVariable Long eventId, 
            @Valid @RequestBody EventRequest eventRequest) {
        log.info("Updating event with ID: {}", eventId);
        EventResponse response = eventService.update(eventId, eventRequest);
        log.info("Event updated with ID: {}", eventId);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes an event.
     * 
     * @param eventId the event ID to delete
     * @return ResponseEntity with HTTP 204 No Content status
     */
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> delete(@PathVariable Long eventId) {
        log.info("Deleting event with ID: {}", eventId);
        eventService.delete(eventId);
        log.info("Event deleted with ID: {}", eventId);
        return ResponseEntity.noContent().build();
    }
}