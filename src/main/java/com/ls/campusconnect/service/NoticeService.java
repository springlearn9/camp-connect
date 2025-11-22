package com.ls.campusconnect.service;

import com.ls.campusconnect.model.entity.Notice;
import com.ls.campusconnect.model.request.NoticeRequest;
import com.ls.campusconnect.model.response.NoticeResponse;
import com.ls.campusconnect.repository.NoticeRepository;
import com.ls.campusconnect.util.CampusServiceUtil;
import com.ls.campusconnect.util.RequestMapper;
import com.ls.campusconnect.util.ResponseMapper;
import com.ls.comitte.model.entity.Member;
import com.ls.comitte.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {
    
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final RequestMapper requestMapper = RequestMapper.INSTANCE;
    private final ResponseMapper responseMapper = ResponseMapper.INSTANCE;
    
    private static final String NOTICE_NOT_FOUND = "Notice not found";
    private static final String MEMBER_NOT_FOUND = "Member not found";
    
    public NoticeResponse get(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException(NOTICE_NOT_FOUND));
        return responseMapper.toResponse(notice);
    }
    
    public List<NoticeResponse> getAll() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<NoticeResponse> getActiveNotices() {
        List<Notice> notices = noticeRepository.findActiveNotices(LocalDateTime.now());
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<NoticeResponse> getHighPriorityNotices() {
        List<Notice> notices = noticeRepository.findHighPriorityActiveNotices(LocalDateTime.now());
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<NoticeResponse> getByStatus(Notice.NoticeStatus status) {
        List<Notice> notices = noticeRepository.findByStatusOrderByCreatedTimestampDesc(status);
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<NoticeResponse> getByCategory(Notice.NoticeCategory category) {
        List<Notice> notices = noticeRepository.findByCategoryOrderByCreatedTimestampDesc(category);
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<NoticeResponse> getByPriority(Notice.NoticePriority priority) {
        List<Notice> notices = noticeRepository.findByPriorityOrderByCreatedTimestampDesc(priority);
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<NoticeResponse> getByPostedBy(Long postedById) {
        List<Notice> notices = noticeRepository.findByPostedByMemberIdOrderByCreatedTimestampDesc(postedById);
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<NoticeResponse> searchByTitle(String title) {
        List<Notice> notices = noticeRepository.findByTitleContainingIgnoreCaseOrderByCreatedTimestampDesc(title);
        return notices.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    @Transactional
    public NoticeResponse create(NoticeRequest noticeRequest) {
        Notice notice = requestMapper.toEntity(noticeRequest);
        
        // Set the posted by member
        Member postedBy = memberRepository.findById(noticeRequest.getPostedById())
                .orElseThrow(() -> new RuntimeException(MEMBER_NOT_FOUND + ": " + noticeRequest.getPostedById()));
        notice.setPostedBy(postedBy);
        
        notice = noticeRepository.save(notice);
        log.info("Created new notice with ID: {}", notice.getId());
        return responseMapper.toResponse(notice);
    }
    
    @Transactional
    public NoticeResponse update(Long noticeId, NoticeRequest noticeRequest) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException(NOTICE_NOT_FOUND));
        
        // Update fields using utility
        CampusServiceUtil.update(notice, noticeRequest);
        
        // Update posted by if provided
        if (noticeRequest.getPostedById() != null) {
            Member postedBy = memberRepository.findById(noticeRequest.getPostedById())
                    .orElseThrow(() -> new RuntimeException(MEMBER_NOT_FOUND + ": " + noticeRequest.getPostedById()));
            notice.setPostedBy(postedBy);
        }
        
        notice = noticeRepository.save(notice);
        log.info("Updated notice with ID: {}", notice.getId());
        return responseMapper.toResponse(notice);
    }
    
    @Transactional
    public void delete(Long noticeId) {
        if (!noticeRepository.existsById(noticeId)) {
            throw new RuntimeException(NOTICE_NOT_FOUND);
        }
        noticeRepository.deleteById(noticeId);
        log.info("Deleted notice with ID: {}", noticeId);
    }
}
