package com.ls.campusconnect.repository;

import com.ls.campusconnect.model.entity.FoundItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
    
    // Override findAll to eagerly fetch reportedBy and claimedBy to avoid N+1 problem
    @Override
    @EntityGraph(attributePaths = {"reportedBy", "claimedBy"})
    List<FoundItem> findAll();
    
    // Find found items by reported member
    @EntityGraph(attributePaths = {"reportedBy", "claimedBy"})
    List<FoundItem> findByReportedByMemberIdOrderByFoundDateDesc(Long reportedById);
    
    // Find found items by status
    @EntityGraph(attributePaths = {"reportedBy", "claimedBy"})
    List<FoundItem> findByStatusOrderByFoundDateDesc(FoundItem.ItemStatus status);
    
    // Find found items by category
    @EntityGraph(attributePaths = {"reportedBy", "claimedBy"})
    List<FoundItem> findByCategoryOrderByFoundDateDesc(String category);
    
    // Find available found items
    @Query("SELECT f FROM FoundItem f JOIN FETCH f.reportedBy LEFT JOIN FETCH f.claimedBy WHERE f.status = 'AVAILABLE' ORDER BY f.foundDate DESC")
    List<FoundItem> findAvailableItems();
    
    // Find found items by item name
    @EntityGraph(attributePaths = {"reportedBy", "claimedBy"})
    List<FoundItem> findByItemNameContainingIgnoreCaseOrderByFoundDateDesc(String itemName);
}

