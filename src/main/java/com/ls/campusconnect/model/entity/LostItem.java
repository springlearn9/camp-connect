package com.ls.campusconnect.model.entity;

import com.ls.comitte.model.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lost_items")
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name is required")
    @Column(name = "item_name")
    private String itemName;

    @Column(length = 1000)
    private String description;

    private String location;

    @Column(name = "lost_date")
    private LocalDateTime lostDate;

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.PENDING;

    @Column(name = "category")
    private String category; // ELECTRONICS, DOCUMENTS, CLOTHING, ACCESSORIES, BOOKS, OTHER

    @Column(name = "reward_amount")
    private Double rewardAmount;

    @Column(name = "contact_info")
    private String contactInfo;

    // Image support
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "additional_images", length = 1000)
    private String additionalImages; // JSON array of image URLs

    // Priority and visibility
    private Boolean urgent = false;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous = false;

    @CreationTimestamp
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_by")
    private Member reportedBy;

    // Status enum for better type safety
    public enum ItemStatus {
        PENDING,    // Just reported as lost
        SEARCHING,  // Actively being searched
        FOUND,      // Someone found it
        CLAIMED,    // Owner has claimed it
        CLOSED      // Case closed (not found or resolved)
    }
}
