package com.ls.campusconnect.model.entity;

import com.ls.auth.model.entity.Member;
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
@Table(name = "notices")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoticePriority priority = NoticePriority.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoticeCategory category = NoticeCategory.GENERAL;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoticeStatus status = NoticeStatus.ACTIVE;

    @Column(name = "attachment_url")
    private String attachmentUrl;

    @CreationTimestamp
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", nullable = false)
    private Member postedBy;

    // Enums for better type safety
    public enum NoticePriority {
        HIGH, NORMAL, LOW
    }

    public enum NoticeCategory {
        ACADEMIC, ADMINISTRATIVE, EVENT, GENERAL
    }

    public enum NoticeStatus {
        ACTIVE, ARCHIVED, DRAFT
    }
}
