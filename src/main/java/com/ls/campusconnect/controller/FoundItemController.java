package com.ls.campusconnect.controller;

import com.ls.campusconnect.model.entity.FoundItem;
import com.ls.campusconnect.model.request.FoundItemRequest;
import com.ls.campusconnect.model.response.FoundItemResponse;
import com.ls.campusconnect.service.FoundItemService;
import com.ls.common.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for managing Found Item resources.
 * 
 * <p>This controller provides CRUD operations for found items including
 * creation, retrieval, updates, and search functionality. Found items can be
 * filtered by status, category, reporter, and other criteria.</p>
 */
@RestController
@RequestMapping("/api/campus/found-items")
@RequiredArgsConstructor
@Slf4j
public class FoundItemController {
    
    private final FoundItemService foundItemService;
    private final FileStorageService fileStorageService;

    /**
     * Creates a new found item.
     * 
     * @param foundItemRequest the found item creation request
     * @return ResponseEntity with FoundItemResponse and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<FoundItemResponse> create(@Valid @RequestBody FoundItemRequest foundItemRequest) {
        log.info("Creating new found item: {}", foundItemRequest.getItemName());
        FoundItemResponse response = foundItemService.create(foundItemRequest);
        log.info("Found item created with ID: {}", response.id());
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Creates a new found item with image upload support.
     * Accepts multipart/form-data with optional photo and additional images.
     * 
     * @param itemName the name of the found item
     * @param description optional description
     * @param location where the item was found
     * @param foundDate when the item was found
     * @param category item category (ELECTRONICS, DOCUMENTS, etc.)
     * @param contactInfo contact information
     * @param distinctiveFeatures distinctive features of the item
     * @param handoverLocation where to handover the item
     * @param verificationRequired whether verification is required
     * @param isAnonymous whether to report anonymously
     * @param reportedById the ID of the member reporting the item
     * @param photo optional main photo of the item
     * @param additionalImages optional additional photos
     * @return ResponseEntity with FoundItemResponse and HTTP 201 status
     */
    @PostMapping(value = "/with-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FoundItemResponse> createWithImages(
            @RequestParam("itemName") String itemName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "foundDate", required = false) String foundDate,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "contactInfo", required = false) String contactInfo,
            @RequestParam(value = "distinctiveFeatures", required = false) String distinctiveFeatures,
            @RequestParam(value = "handoverLocation", required = false) String handoverLocation,
            @RequestParam(value = "verificationRequired", required = false) Boolean verificationRequired,
            @RequestParam(value = "isAnonymous", required = false) Boolean isAnonymous,
            @RequestParam("reportedById") Long reportedById,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "additionalImages", required = false) MultipartFile[] additionalImages) {
        
        log.info("Creating new found item with images: {}", itemName);
        
        // Store main photo if provided
        String photoUrl = null;
        if (photo != null && !photo.isEmpty()) {
            photoUrl = fileStorageService.storeFile(photo);
            log.info("Main photo stored: {}", photoUrl);
        }
        
        // Store additional images if provided
        String additionalImagesStr = null;
        if (additionalImages != null && additionalImages.length > 0) {
            additionalImagesStr = fileStorageService.storeFiles(additionalImages);
            log.info("Additional images stored: {}", additionalImagesStr);
        }
        
        // Parse foundDate if provided
        LocalDateTime parsedFoundDate = null;
        if (foundDate != null && !foundDate.isEmpty()) {
            try {
                parsedFoundDate = LocalDateTime.parse(foundDate);
            } catch (Exception e) {
                log.warn("Failed to parse foundDate, using null: {}", foundDate);
            }
        }
        
        // Create request object
        FoundItemRequest request = new FoundItemRequest(
            itemName,
            description,
            location,
            parsedFoundDate,
            null, // status will be set to default AVAILABLE
            category,
            contactInfo,
            photoUrl,
            additionalImagesStr,
            distinctiveFeatures,
            handoverLocation,
            verificationRequired,
            isAnonymous,
            reportedById
        );
        
        FoundItemResponse response = foundItemService.create(request);
        log.info("Found item created with ID: {} and images", response.id());
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Retrieves a found item by its ID.
     * 
     * @param foundItemId the found item ID
     * @return ResponseEntity with FoundItemResponse and HTTP 200 status
     */
    @GetMapping("/{foundItemId}")
    public ResponseEntity<FoundItemResponse> get(@PathVariable Long foundItemId) {
        log.info("Retrieving found item with ID: {}", foundItemId);
        FoundItemResponse response = foundItemService.get(foundItemId);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all found items.
     * 
     * @return ResponseEntity with list of FoundItemResponse and HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<FoundItemResponse>> getAll() {
        log.info("Retrieving all found items");
        List<FoundItemResponse> responses = foundItemService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves available found items (not yet claimed).
     * 
     * @return ResponseEntity with list of FoundItemResponse and HTTP 200 status
     */
    @GetMapping("/available")
    public ResponseEntity<List<FoundItemResponse>> getAvailable() {
        log.info("Retrieving available found items");
        List<FoundItemResponse> responses = foundItemService.getAvailableItems();
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves found items by status.
     * 
     * @param status the item status
     * @return ResponseEntity with list of FoundItemResponse and HTTP 200 status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FoundItemResponse>> getByStatus(@PathVariable String status) {
        log.info("Retrieving found items with status: {}", status);
        FoundItem.ItemStatus itemStatus = FoundItem.ItemStatus.valueOf(status.toUpperCase());
        List<FoundItemResponse> responses = foundItemService.getByStatus(itemStatus);
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves found items by category.
     * 
     * @param category the item category
     * @return ResponseEntity with list of FoundItemResponse and HTTP 200 status
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<FoundItemResponse>> getByCategory(@PathVariable String category) {
        log.info("Retrieving found items in category: {}", category);
        List<FoundItemResponse> responses = foundItemService.getByCategory(category);
        return ResponseEntity.ok(responses);
    }

    /**
     * Retrieves found items by reporter.
     * 
     * @param reportedById the member ID who reported the items
     * @return ResponseEntity with list of FoundItemResponse and HTTP 200 status
     */
    @GetMapping("/reported-by/{reportedById}")
    public ResponseEntity<List<FoundItemResponse>> getByReportedBy(@PathVariable Long reportedById) {
        log.info("Retrieving found items reported by member ID: {}", reportedById);
        List<FoundItemResponse> responses = foundItemService.getByReportedBy(reportedById);
        return ResponseEntity.ok(responses);
    }

    /**
     * Searches found items by item name.
     * 
     * @param itemName the item name keyword to search
     * @return ResponseEntity with list of FoundItemResponse and HTTP 200 status
     */
    @GetMapping("/search")
    public ResponseEntity<List<FoundItemResponse>> searchByItemName(@RequestParam String itemName) {
        log.info("Searching found items by name: {}", itemName);
        List<FoundItemResponse> responses = foundItemService.searchByItemName(itemName);
        return ResponseEntity.ok(responses);
    }

    /**
     * Updates an existing found item.
     * 
     * @param foundItemId the found item ID to update
     * @param foundItemRequest the update request
     * @return ResponseEntity with updated FoundItemResponse and HTTP 200 status
     */
    @PutMapping("/{foundItemId}")
    public ResponseEntity<FoundItemResponse> update(
            @PathVariable Long foundItemId, 
            @Valid @RequestBody FoundItemRequest foundItemRequest) {
        log.info("Updating found item with ID: {}", foundItemId);
        FoundItemResponse response = foundItemService.update(foundItemId, foundItemRequest);
        log.info("Found item updated with ID: {}", foundItemId);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a found item.
     * 
     * @param foundItemId the found item ID to delete
     * @return ResponseEntity with HTTP 204 No Content status
     */
    @DeleteMapping("/{foundItemId}")
    public ResponseEntity<Void> delete(@PathVariable Long foundItemId) {
        log.info("Deleting found item with ID: {}", foundItemId);
        foundItemService.delete(foundItemId);
        log.info("Found item deleted with ID: {}", foundItemId);
        return ResponseEntity.noContent().build();
    }
}