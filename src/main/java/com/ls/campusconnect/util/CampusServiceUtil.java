package com.ls.campusconnect.util;

import com.ls.campusconnect.model.entity.*;
import com.ls.campusconnect.model.request.*;

public class CampusServiceUtil {

    public static void update(Event event, EventRequest eventRequest) {
        // Note: postedBy relationship is handled in EventService
        if (eventRequest.getTitle() != null) {
            event.setTitle(eventRequest.getTitle());
        }
        if (eventRequest.getDescription() != null) {
            event.setDescription(eventRequest.getDescription());
        }
        if (eventRequest.getEventDate() != null) {
            event.setEventDate(eventRequest.getEventDate());
        }
        if (eventRequest.getLocation() != null) {
            event.setLocation(eventRequest.getLocation());
        }
        if (eventRequest.getImageUrl() != null) {
            event.setImageUrl(eventRequest.getImageUrl());
        }
        if (eventRequest.getStatus() != null) {
            event.setStatus(eventRequest.getStatus());
        }
    }

    public static void update(FoundItem foundItem, FoundItemRequest foundItemRequest) {
        // Note: reportedBy and claimedBy relationships are handled in FoundItemService
        if (foundItemRequest.getItemName() != null) {
            foundItem.setItemName(foundItemRequest.getItemName());
        }
        if (foundItemRequest.getDescription() != null) {
            foundItem.setDescription(foundItemRequest.getDescription());
        }
        if (foundItemRequest.getLocation() != null) {
            foundItem.setLocation(foundItemRequest.getLocation());
        }
        if (foundItemRequest.getFoundDate() != null) {
            foundItem.setFoundDate(foundItemRequest.getFoundDate());
        }
        if (foundItemRequest.getStatus() != null) {
            try {
                foundItem.setStatus(FoundItem.ItemStatus.valueOf(foundItemRequest.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Keep existing status if invalid status provided
            }
        }
        if (foundItemRequest.getCategory() != null) {
            foundItem.setCategory(foundItemRequest.getCategory());
        }
        if (foundItemRequest.getContactInfo() != null) {
            foundItem.setContactInfo(foundItemRequest.getContactInfo());
        }
        if (foundItemRequest.getPhotoUrl() != null) {
            foundItem.setPhotoUrl(foundItemRequest.getPhotoUrl());
        }
        if (foundItemRequest.getAdditionalImages() != null) {
            foundItem.setAdditionalImages(foundItemRequest.getAdditionalImages());
        }
        if (foundItemRequest.getDistinctiveFeatures() != null) {
            foundItem.setDistinctiveFeatures(foundItemRequest.getDistinctiveFeatures());
        }
        if (foundItemRequest.getHandoverLocation() != null) {
            foundItem.setHandoverLocation(foundItemRequest.getHandoverLocation());
        }
        if (foundItemRequest.getVerificationRequired() != null) {
            foundItem.setVerificationRequired(foundItemRequest.getVerificationRequired());
        }
        if (foundItemRequest.getIsAnonymous() != null) {
            foundItem.setIsAnonymous(foundItemRequest.getIsAnonymous());
        }
    }

    public static void update(LostItem lostItem, LostItemRequest lostItemRequest) {
        // Note: reportedBy relationship is handled in LostItemService
        if (lostItemRequest.getItemName() != null) {
            lostItem.setItemName(lostItemRequest.getItemName());
        }
        if (lostItemRequest.getDescription() != null) {
            lostItem.setDescription(lostItemRequest.getDescription());
        }
        if (lostItemRequest.getLocation() != null) {
            lostItem.setLocation(lostItemRequest.getLocation());
        }
        if (lostItemRequest.getLostDate() != null) {
            lostItem.setLostDate(lostItemRequest.getLostDate());
        }
        if (lostItemRequest.getStatus() != null) {
            try {
                lostItem.setStatus(LostItem.ItemStatus.valueOf(lostItemRequest.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Keep existing status if invalid status provided
            }
        }
        if (lostItemRequest.getCategory() != null) {
            lostItem.setCategory(lostItemRequest.getCategory());
        }
        if (lostItemRequest.getRewardAmount() != null) {
            lostItem.setRewardAmount(lostItemRequest.getRewardAmount());
        }
        if (lostItemRequest.getContactInfo() != null) {
            lostItem.setContactInfo(lostItemRequest.getContactInfo());
        }
        if (lostItemRequest.getImageUrl() != null) {
            lostItem.setImageUrl(lostItemRequest.getImageUrl());
        }
        if (lostItemRequest.getAdditionalImages() != null) {
            lostItem.setAdditionalImages(lostItemRequest.getAdditionalImages());
        }
        if (lostItemRequest.getUrgent() != null) {
            lostItem.setUrgent(lostItemRequest.getUrgent());
        }
        if (lostItemRequest.getIsAnonymous() != null) {
            lostItem.setIsAnonymous(lostItemRequest.getIsAnonymous());
        }
    }

    public static void update(Notice notice, NoticeRequest noticeRequest) {
        // Note: postedBy relationship is handled in NoticeService
        if (noticeRequest.getTitle() != null) {
            notice.setTitle(noticeRequest.getTitle());
        }
        if (noticeRequest.getDescription() != null) {
            notice.setDescription(noticeRequest.getDescription());
        }
        if (noticeRequest.getPriority() != null) {
            try {
                notice.setPriority(Notice.NoticePriority.valueOf(noticeRequest.getPriority().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Keep existing priority if invalid priority provided
            }
        }
        if (noticeRequest.getCategory() != null) {
            try {
                notice.setCategory(Notice.NoticeCategory.valueOf(noticeRequest.getCategory().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Keep existing category if invalid category provided
            }
        }
        if (noticeRequest.getValidUntil() != null) {
            notice.setValidUntil(noticeRequest.getValidUntil());
        }
        if (noticeRequest.getStatus() != null) {
            try {
                notice.setStatus(Notice.NoticeStatus.valueOf(noticeRequest.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Keep existing status if invalid status provided
            }
        }
        if (noticeRequest.getAttachmentUrl() != null) {
            notice.setAttachmentUrl(noticeRequest.getAttachmentUrl());
        }
    }

    /**
     * Helper method to safely convert string to enum
     * @param enumValue string value to convert
     * @param enumClass target enum class
     * @param defaultValue default value if conversion fails
     * @return converted enum value or default
     */
    public static <T extends Enum<T>> T safeEnumConversion(String enumValue, Class<T> enumClass, T defaultValue) {
        if (enumValue == null || enumValue.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Enum.valueOf(enumClass, enumValue.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    /**
     * Helper method to check if a string is not null and not empty
     * @param value string to check
     * @return true if string has content, false otherwise
     */
    public static boolean hasContent(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Helper method to safely update string fields only if they have content
     * @param setter consumer to set the value
     * @param newValue new value to set
     */
    public static void updateIfHasContent(java.util.function.Consumer<String> setter, String newValue) {
        if (hasContent(newValue)) {
            setter.accept(newValue.trim());
        }
    }
}