package com.ls.campusconnect.repository;

import com.ls.campusconnect.model.entity.Notice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // Override findAll to eagerly fetch postedBy to avoid N+1 problem
    @Override
    @EntityGraph(attributePaths = {"postedBy"})
    List<Notice> findAll();

    // Find notices by status
    @EntityGraph(attributePaths = {"postedBy"})
    List<Notice> findByStatusOrderByCreatedTimestampDesc(Notice.NoticeStatus status);

    // Find notices by category
    @EntityGraph(attributePaths = {"postedBy"})
    List<Notice> findByCategoryOrderByCreatedTimestampDesc(Notice.NoticeCategory category);

    // Find notices by priority
    @EntityGraph(attributePaths = {"postedBy"})
    List<Notice> findByPriorityOrderByCreatedTimestampDesc(Notice.NoticePriority priority);

    // Find active notices (not expired)
    @Query("SELECT n FROM Notice n JOIN FETCH n.postedBy WHERE n.status = 'ACTIVE' AND (n.validUntil IS NULL OR n.validUntil > :currentDate) ORDER BY n.createdTimestamp DESC")
    List<Notice> findActiveNotices(@Param("currentDate") LocalDateTime currentDate);

    // Find notices by title containing keyword
    @EntityGraph(attributePaths = {"postedBy"})
    List<Notice> findByTitleContainingIgnoreCaseOrderByCreatedTimestampDesc(String keyword);

    // Find notices by posted user
    @EntityGraph(attributePaths = {"postedBy"})
    List<Notice> findByPostedByMemberIdOrderByCreatedTimestampDesc(Long postedById);

    // Find high priority active notices
    @Query("SELECT n FROM Notice n JOIN FETCH n.postedBy WHERE n.priority = 'HIGH' AND n.status = 'ACTIVE' AND (n.validUntil IS NULL OR n.validUntil > :currentDate) ORDER BY n.createdTimestamp DESC")
    List<Notice> findHighPriorityActiveNotices(@Param("currentDate") LocalDateTime currentDate);
}