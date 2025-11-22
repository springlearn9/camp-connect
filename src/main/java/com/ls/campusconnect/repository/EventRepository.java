package com.ls.campusconnect.repository;

import com.ls.campusconnect.model.entity.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Override findAll to eagerly fetch postedBy to avoid N+1 problem
    @Override
    @EntityGraph(attributePaths = {"postedBy"})
    List<Event> findAll();

    // Find events by status
    @EntityGraph(attributePaths = {"postedBy"})
    List<Event> findByStatus(String status);

    // Find upcoming events
    @Query("SELECT e FROM Event e JOIN FETCH e.postedBy WHERE e.eventDate > :currentDate AND e.status = 'ACTIVE' ORDER BY e.eventDate ASC")
    List<Event> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);

    // Find events by date range
    @Query("SELECT e FROM Event e JOIN FETCH e.postedBy WHERE e.eventDate BETWEEN :startDate AND :endDate ORDER BY e.eventDate ASC")
    List<Event> findEventsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Find events by title containing keyword
    @EntityGraph(attributePaths = {"postedBy"})
    List<Event> findByTitleContainingIgnoreCase(String keyword);

    // Find events by posted user
    @EntityGraph(attributePaths = {"postedBy"})
    List<Event> findByPostedByMemberIdOrderByCreatedTimestampDesc(Long postedById);

    // Find events by location
    @EntityGraph(attributePaths = {"postedBy"})
    List<Event> findByLocationContainingIgnoreCase(String location);
}