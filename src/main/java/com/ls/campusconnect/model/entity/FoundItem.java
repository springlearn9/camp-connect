package com.ls.campusconnect.model.entity;

import com.ls.comitte.model.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "found_items")
public class FoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name is required")
    @Column(name = "item_name")
    private String itemName;

    @Column(length = 1000)
    private String description;

    private String location;

    @Column(name = "found_date")
    private LocalDateTime foundDate;

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.AVAILABLE;

    @Column(name = "category")
    private String category; // ELECTRONICS, DOCUMENTS, CLOTHING, ACCESSORIES, BOOKS, OTHER

    @Column(name = "contact_info")
    private String contactInfo;

    // Enhanced image support
    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "additional_images", length = 1000)
    private String additionalImages; // JSON array of image URLs

    // Additional details
    @Column(name = "distinctive_features")
    private String distinctiveFeatures;

    @Column(name = "handover_location")
    private String handoverLocation;

    @Column(name = "verification_required")
    private Boolean verificationRequired = true;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "found_item_id")
    private List<Member> claimedBy = new ArrayList<>();

    @Column(name = "claimed_at")
    private LocalDateTime claimedAt;

    // Status enum for found items
    public enum ItemStatus {
        AVAILABLE,  // Found item is available for claim
        CLAIMED,    // Item has been claimed by someone
        VERIFIED,   // Ownership has been verified and item handed over
        EXPIRED     // Item posting has expired
    }
}
