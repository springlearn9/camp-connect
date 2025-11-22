package com.ls.campusconnect.model.entity;

import com.ls.comitte.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Column(nullable = false)
    private String location;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private String status = "ACTIVE"; // ACTIVE, CANCELLED, COMPLETED

    @CreatedDate
    private LocalDateTime createdTimestamp;

    @LastModifiedDate
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", nullable = false)
    private Member postedBy;
}
