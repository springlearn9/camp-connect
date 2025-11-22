package com.ls.campusconnect.repository;

import com.ls.campusconnect.model.entity.LostItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    
    // Override findAll to eagerly fetch reportedBy to avoid N+1 problem
    @Override
    @EntityGraph(attributePaths = {"reportedBy"})
    List<LostItem> findAll();
    
    // Find lost items by reporting member
    @EntityGraph(attributePaths = {"reportedBy"})
    List<LostItem> findByReportedByMemberIdOrderByLostDateDesc(Long reportedById);
    
    // Find lost items by status
    @EntityGraph(attributePaths = {"reportedBy"})
    List<LostItem> findByStatusOrderByLostDateDesc(LostItem.ItemStatus status);
    
    // Find lost items by category
    @EntityGraph(attributePaths = {"reportedBy"})
    List<LostItem> findByCategoryOrderByLostDateDesc(String category);
    
    // Find active lost items
    @Query("SELECT l FROM LostItem l JOIN FETCH l.reportedBy WHERE l.status IN ('PENDING', 'SEARCHING') ORDER BY l.lostDate DESC")
    List<LostItem> findActiveItems();
    
    // Find urgent lost items
    @EntityGraph(attributePaths = {"reportedBy"})
    List<LostItem> findByUrgentTrueOrderByLostDateDesc();
    
    // Find lost items by item name
    @EntityGraph(attributePaths = {"reportedBy"})
    List<LostItem> findByItemNameContainingIgnoreCaseOrderByLostDateDesc(String itemName);
}
