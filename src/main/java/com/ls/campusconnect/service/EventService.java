package com.ls.campusconnect.service;

import com.ls.campusconnect.model.entity.Event;
import com.ls.campusconnect.model.request.EventRequest;
import com.ls.campusconnect.model.response.EventResponse;
import com.ls.campusconnect.repository.EventRepository;
import com.ls.campusconnect.util.CampusServiceUtil;
import com.ls.campusconnect.util.RequestMapper;
import com.ls.campusconnect.util.ResponseMapper;
import com.ls.auth.model.entity.Member;
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
public class EventService {
    
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final RequestMapper requestMapper = RequestMapper.INSTANCE;
    private final ResponseMapper responseMapper = ResponseMapper.INSTANCE;
    
    private static final String EVENT_NOT_FOUND = "Event not found";
    private static final String MEMBER_NOT_FOUND = "Member not found";
    
    public EventResponse get(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException(EVENT_NOT_FOUND));
        return responseMapper.toResponse(event);
    }
    
    public List<EventResponse> getAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<EventResponse> getUpcomingEvents() {
        List<Event> events = eventRepository.findUpcomingEvents(LocalDateTime.now());
        return events.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<EventResponse> getEventsByStatus(String status) {
        List<Event> events = eventRepository.findByStatus(status);
        return events.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<EventResponse> getEventsByPostedBy(Long postedById) {
        List<Event> events = eventRepository.findByPostedByMemberIdOrderByCreatedTimestampDesc(postedById);
        return events.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<EventResponse> searchEventsByTitle(String title) {
        List<Event> events = eventRepository.findByTitleContainingIgnoreCase(title);
        return events.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    public List<EventResponse> searchEventsByLocation(String location) {
        List<Event> events = eventRepository.findByLocationContainingIgnoreCase(location);
        return events.stream()
                .map(responseMapper::toResponse)
                .toList();
    }
    
    @Transactional
    public EventResponse create(EventRequest eventRequest) {
        Event event = requestMapper.toEntity(eventRequest);
        
        // Set the posted by member - use provided ID or default to Campus Administrator (ID 11)
        Long postedById = eventRequest.getPostedById();
        if (postedById == null) {
            postedById = 11L; // Default to Campus Administrator
        }
        
        // If member not found, fall back to Campus Administrator
        Member postedBy = memberRepository.findById(postedById)
                .orElseGet(() -> memberRepository.findById(11L)
                        .orElseThrow(() -> new RuntimeException("Default member (ID 11) not found in database")));
        event.setPostedBy(postedBy);
        
        event = eventRepository.save(event);
        log.info("Created new event with ID: {}", event.getId());
        return responseMapper.toResponse(event);
    }
    
    @Transactional
    public EventResponse update(Long eventId, EventRequest eventRequest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException(EVENT_NOT_FOUND));
        
        // Update fields using utility
        CampusServiceUtil.update(event, eventRequest);
        
        // Update posted by if provided
        if (eventRequest.getPostedById() != null) {
            Member postedBy = memberRepository.findById(eventRequest.getPostedById())
                    .orElseThrow(() -> new RuntimeException(MEMBER_NOT_FOUND + ": " + eventRequest.getPostedById()));
            event.setPostedBy(postedBy);
        }
        
        event = eventRepository.save(event);
        log.info("Updated event with ID: {}", event.getId());
        return responseMapper.toResponse(event);
    }
    
    @Transactional
    public void delete(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException(EVENT_NOT_FOUND);
        }
        eventRepository.deleteById(eventId);
        log.info("Deleted event with ID: {}", eventId);
    }
}
