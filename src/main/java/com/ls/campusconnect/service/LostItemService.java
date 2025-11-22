package com.ls.campusconnect.service;

import com.ls.campusconnect.model.entity.LostItem;
import com.ls.campusconnect.model.request.LostItemRequest;
import com.ls.campusconnect.model.response.LostItemResponse;
import com.ls.campusconnect.repository.LostItemRepository;
import com.ls.campusconnect.util.CampusServiceUtil;
import com.ls.campusconnect.util.RequestMapper;
import com.ls.campusconnect.util.ResponseMapper;
import com.ls.comitte.model.entity.Member;
import com.ls.comitte.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LostItemService {
    
    private final LostItemRepository lostItemRepository;
    private final MemberRepository memberRepository;
    private final RequestMapper requestMapper = RequestMapper.INSTANCE;
    private final ResponseMapper responseMapper = ResponseMapper.INSTANCE;
    
    private static final String LOST_ITEM_NOT_FOUND = "Lost item not found";
    private static final String MEMBER_NOT_FOUND = "Member not found";
    
    public LostItemResponse get(Long lostItemId) {
        LostItem lostItem = lostItemRepository.findById(lostItemId)
                .orElseThrow(() -> new RuntimeException(LOST_ITEM_NOT_FOUND));
        return responseMapper.toResponse(lostItem);
    }
    
    public List<LostItemResponse> getAll() {
        List<LostItem> lostItems = lostItemRepository.findAll();
        return lostItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<LostItemResponse> getActiveItems() {
        List<LostItem> lostItems = lostItemRepository.findActiveItems();
        return lostItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<LostItemResponse> getUrgentItems() {
        List<LostItem> lostItems = lostItemRepository.findByUrgentTrueOrderByLostDateDesc();
        return lostItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<LostItemResponse> getByStatus(LostItem.ItemStatus status) {
        List<LostItem> lostItems = lostItemRepository.findByStatusOrderByLostDateDesc(status);
        return lostItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<LostItemResponse> getByReportedBy(Long reportedById) {
        List<LostItem> lostItems = lostItemRepository.findByReportedByMemberIdOrderByLostDateDesc(reportedById);
        return lostItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<LostItemResponse> getByCategory(String category) {
        List<LostItem> lostItems = lostItemRepository.findByCategoryOrderByLostDateDesc(category);
        return lostItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<LostItemResponse> searchByItemName(String itemName) {
        List<LostItem> lostItems = lostItemRepository.findByItemNameContainingIgnoreCaseOrderByLostDateDesc(itemName);
        return lostItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    @Transactional
    public LostItemResponse create(LostItemRequest lostItemRequest) {
        LostItem lostItem = requestMapper.toEntity(lostItemRequest);
        
        // Set the reported by member (using userId field)
        Member reportedBy = memberRepository.findById(lostItemRequest.getUserId())
                .orElseThrow(() -> new RuntimeException(MEMBER_NOT_FOUND + ": " + lostItemRequest.getUserId()));
        lostItem.setReportedBy(reportedBy);
        
        lostItem = lostItemRepository.save(lostItem);
        log.info("Created new lost item with ID: {}", lostItem.getId());
        return responseMapper.toResponse(lostItem);
    }
    
    @Transactional
    public LostItemResponse update(Long lostItemId, LostItemRequest lostItemRequest) {
        LostItem lostItem = lostItemRepository.findById(lostItemId)
                .orElseThrow(() -> new RuntimeException(LOST_ITEM_NOT_FOUND));
        
        // Update fields using utility
        CampusServiceUtil.update(lostItem, lostItemRequest);
        
        // Update reported by if provided
        if (lostItemRequest.getUserId() != null) {
            Member reportedBy = memberRepository.findById(lostItemRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException(MEMBER_NOT_FOUND + ": " + lostItemRequest.getUserId()));
            lostItem.setReportedBy(reportedBy);
        }
        
        lostItem = lostItemRepository.save(lostItem);
        log.info("Updated lost item with ID: {}", lostItem.getId());
        return responseMapper.toResponse(lostItem);
    }
    
    @Transactional
    public void delete(Long lostItemId) {
        if (!lostItemRepository.existsById(lostItemId)) {
            throw new RuntimeException(LOST_ITEM_NOT_FOUND);
        }
        lostItemRepository.deleteById(lostItemId);
        log.info("Deleted lost item with ID: {}", lostItemId);
    }
}
