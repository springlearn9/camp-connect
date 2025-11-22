package com.ls.campusconnect.service;

import com.ls.campusconnect.model.entity.FoundItem;
import com.ls.campusconnect.model.request.FoundItemRequest;
import com.ls.campusconnect.model.response.FoundItemResponse;
import com.ls.campusconnect.repository.FoundItemRepository;
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
public class FoundItemService {
    
    private final FoundItemRepository foundItemRepository;
    private final MemberRepository memberRepository;
    private final RequestMapper requestMapper = RequestMapper.INSTANCE;
    private final ResponseMapper responseMapper = ResponseMapper.INSTANCE;
    
    private static final String FOUND_ITEM_NOT_FOUND = "Found item not found";
    private static final String MEMBER_NOT_FOUND = "Member not found";
    
    public FoundItemResponse get(Long foundItemId) {
        FoundItem foundItem = foundItemRepository.findById(foundItemId)
                .orElseThrow(() -> new RuntimeException(FOUND_ITEM_NOT_FOUND));
        return responseMapper.toResponse(foundItem);
    }
    
    public List<FoundItemResponse> getAll() {
        List<FoundItem> foundItems = foundItemRepository.findAll();
        return foundItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<FoundItemResponse> getAvailableItems() {
        List<FoundItem> foundItems = foundItemRepository.findAvailableItems();
        return foundItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<FoundItemResponse> getByStatus(FoundItem.ItemStatus status) {
        List<FoundItem> foundItems = foundItemRepository.findByStatusOrderByFoundDateDesc(status);
        return foundItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<FoundItemResponse> getByReportedBy(Long reportedById) {
        List<FoundItem> foundItems = foundItemRepository.findByReportedByMemberIdOrderByFoundDateDesc(reportedById);
        return foundItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<FoundItemResponse> getByCategory(String category) {
        List<FoundItem> foundItems = foundItemRepository.findByCategoryOrderByFoundDateDesc(category);
        return foundItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<FoundItemResponse> searchByItemName(String itemName) {
        List<FoundItem> foundItems = foundItemRepository.findByItemNameContainingIgnoreCaseOrderByFoundDateDesc(itemName);
        return foundItems.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    @Transactional
    public FoundItemResponse create(FoundItemRequest foundItemRequest) {
        FoundItem foundItem = requestMapper.toEntity(foundItemRequest);
        
        // Set the reported by member
        Member reportedBy = memberRepository.findById(foundItemRequest.getReportedById())
                .orElseThrow(() -> new RuntimeException(MEMBER_NOT_FOUND + ": " + foundItemRequest.getReportedById()));
        foundItem.setReportedBy(reportedBy);
        
        foundItem = foundItemRepository.save(foundItem);
        log.info("Created new found item with ID: {}", foundItem.getId());
        return responseMapper.toResponse(foundItem);
    }
    
    @Transactional
    public FoundItemResponse update(Long foundItemId, FoundItemRequest foundItemRequest) {
        FoundItem foundItem = foundItemRepository.findById(foundItemId)
                .orElseThrow(() -> new RuntimeException(FOUND_ITEM_NOT_FOUND));
        
        // Update fields using utility
        CampusServiceUtil.update(foundItem, foundItemRequest);
        
        // Update reported by if provided
        if (foundItemRequest.getReportedById() != null) {
            Member reportedBy = memberRepository.findById(foundItemRequest.getReportedById())
                    .orElseThrow(() -> new RuntimeException(MEMBER_NOT_FOUND + ": " + foundItemRequest.getReportedById()));
            foundItem.setReportedBy(reportedBy);
        }
        
        foundItem = foundItemRepository.save(foundItem);
        log.info("Updated found item with ID: {}", foundItem.getId());
        return responseMapper.toResponse(foundItem);
    }
    
    @Transactional
    public void delete(Long foundItemId) {
        if (!foundItemRepository.existsById(foundItemId)) {
            throw new RuntimeException(FOUND_ITEM_NOT_FOUND);
        }
        foundItemRepository.deleteById(foundItemId);
        log.info("Deleted found item with ID: {}", foundItemId);
    }
}
