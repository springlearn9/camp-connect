package com.ls.campusconnect.controller;

import com.ls.campusconnect.model.entity.LostItem;
import com.ls.campusconnect.model.request.LostItemRequest;
import com.ls.campusconnect.model.response.LostItemResponse;
import com.ls.campusconnect.service.LostItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

/**
 * REST controller for managing Lost Item resources.
 */
@RestController
@RequestMapping("/api/campus/lost-items")
@RequiredArgsConstructor
@Slf4j
public class LostItemController {
    
    private final LostItemService lostItemService;

    @PostMapping
    public ResponseEntity<LostItemResponse> create(@Valid @RequestBody LostItemRequest lostItemRequest) {
        log.info("Creating new lost item: {}", lostItemRequest.getItemName());
        LostItemResponse response = lostItemService.create(lostItemRequest);
        log.info("Lost item created with ID: {}", response.id());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{lostItemId}")
    public ResponseEntity<LostItemResponse> get(@PathVariable Long lostItemId) {
        log.info("Retrieving lost item with ID: {}", lostItemId);
        LostItemResponse response = lostItemService.get(lostItemId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LostItemResponse>> getAll() {
        log.info("Retrieving all lost items");
        List<LostItemResponse> responses = lostItemService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/active")
    public ResponseEntity<List<LostItemResponse>> getActive() {
        log.info("Retrieving active lost items");
        List<LostItemResponse> responses = lostItemService.getActiveItems();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/urgent")
    public ResponseEntity<List<LostItemResponse>> getUrgent() {
        log.info("Retrieving urgent lost items");
        List<LostItemResponse> responses = lostItemService.getUrgentItems();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LostItemResponse>> getByStatus(@PathVariable String status) {
        log.info("Retrieving lost items with status: {}", status);
        LostItem.ItemStatus itemStatus = LostItem.ItemStatus.valueOf(status.toUpperCase());
        List<LostItemResponse> responses = lostItemService.getByStatus(itemStatus);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<LostItemResponse>> getByCategory(@PathVariable String category) {
        log.info("Retrieving lost items in category: {}", category);
        List<LostItemResponse> responses = lostItemService.getByCategory(category);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/reported-by/{reportedById}")
    public ResponseEntity<List<LostItemResponse>> getByReportedBy(@PathVariable Long reportedById) {
        log.info("Retrieving lost items reported by member ID: {}", reportedById);
        List<LostItemResponse> responses = lostItemService.getByReportedBy(reportedById);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<LostItemResponse>> searchByItemName(@RequestParam String itemName) {
        log.info("Searching lost items by name: {}", itemName);
        List<LostItemResponse> responses = lostItemService.searchByItemName(itemName);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{lostItemId}")
    public ResponseEntity<LostItemResponse> update(
            @PathVariable Long lostItemId, 
            @Valid @RequestBody LostItemRequest lostItemRequest) {
        log.info("Updating lost item with ID: {}", lostItemId);
        LostItemResponse response = lostItemService.update(lostItemId, lostItemRequest);
        log.info("Lost item updated with ID: {}", lostItemId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{lostItemId}")
    public ResponseEntity<Void> delete(@PathVariable Long lostItemId) {
        log.info("Deleting lost item with ID: {}", lostItemId);
        lostItemService.delete(lostItemId);
        log.info("Lost item deleted with ID: {}", lostItemId);
        return ResponseEntity.noContent().build();
    }
}