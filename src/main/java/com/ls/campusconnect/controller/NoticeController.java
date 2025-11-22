package com.ls.campusconnect.controller;

import com.ls.campusconnect.model.entity.Notice;
import com.ls.campusconnect.model.request.NoticeRequest;
import com.ls.campusconnect.model.response.NoticeResponse;
import com.ls.campusconnect.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

/**
 * REST controller for managing Notice resources.
 */
@RestController
@RequestMapping("/api/campus/notices")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
    
    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<NoticeResponse> create(@Valid @RequestBody NoticeRequest noticeRequest) {
        log.info("Creating new notice: {}", noticeRequest.getTitle());
        NoticeResponse response = noticeService.create(noticeRequest);
        log.info("Notice created with ID: {}", response.id());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> get(@PathVariable Long noticeId) {
        log.info("Retrieving notice with ID: {}", noticeId);
        NoticeResponse response = noticeService.get(noticeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NoticeResponse>> getAll() {
        log.info("Retrieving all notices");
        List<NoticeResponse> responses = noticeService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/active")
    public ResponseEntity<List<NoticeResponse>> getActive() {
        log.info("Retrieving active notices");
        List<NoticeResponse> responses = noticeService.getActiveNotices();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/high-priority")
    public ResponseEntity<List<NoticeResponse>> getHighPriority() {
        log.info("Retrieving high priority notices");
        List<NoticeResponse> responses = noticeService.getHighPriorityNotices();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NoticeResponse>> getByStatus(@PathVariable String status) {
        log.info("Retrieving notices with status: {}", status);
        Notice.NoticeStatus noticeStatus = Notice.NoticeStatus.valueOf(status.toUpperCase());
        List<NoticeResponse> responses = noticeService.getByStatus(noticeStatus);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<NoticeResponse>> getByCategory(@PathVariable String category) {
        log.info("Retrieving notices in category: {}", category);
        Notice.NoticeCategory noticeCategory = Notice.NoticeCategory.valueOf(category.toUpperCase());
        List<NoticeResponse> responses = noticeService.getByCategory(noticeCategory);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<NoticeResponse>> getByPriority(@PathVariable String priority) {
        log.info("Retrieving notices with priority: {}", priority);
        Notice.NoticePriority noticePriority = Notice.NoticePriority.valueOf(priority.toUpperCase());
        List<NoticeResponse> responses = noticeService.getByPriority(noticePriority);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/posted-by/{postedById}")
    public ResponseEntity<List<NoticeResponse>> getByPostedBy(@PathVariable Long postedById) {
        log.info("Retrieving notices posted by member ID: {}", postedById);
        List<NoticeResponse> responses = noticeService.getByPostedBy(postedById);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<NoticeResponse>> searchByTitle(@RequestParam String title) {
        log.info("Searching notices by title: {}", title);
        List<NoticeResponse> responses = noticeService.searchByTitle(title);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> update(
            @PathVariable Long noticeId, 
            @Valid @RequestBody NoticeRequest noticeRequest) {
        log.info("Updating notice with ID: {}", noticeId);
        NoticeResponse response = noticeService.update(noticeId, noticeRequest);
        log.info("Notice updated with ID: {}", noticeId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> delete(@PathVariable Long noticeId) {
        log.info("Deleting notice with ID: {}", noticeId);
        noticeService.delete(noticeId);
        log.info("Notice deleted with ID: {}", noticeId);
        return ResponseEntity.noContent().build();
    }
}