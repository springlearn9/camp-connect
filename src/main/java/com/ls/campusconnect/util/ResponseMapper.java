package com.ls.campusconnect.util;

import com.ls.campusconnect.model.entity.*;
import com.ls.campusconnect.model.response.*;
import com.ls.auth.model.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    @Mapping(source = "postedBy.memberId", target = "postedById")
    @Mapping(source = "postedBy.name", target = "postedByName")
    EventResponse toResponse(Event event);

    @Mapping(source = "reportedBy.memberId", target = "reportedById")
    @Mapping(source = "reportedBy.name", target = "reportedByName")
    @Mapping(target = "claimedByIds", expression = "java(extractMemberIds(foundItem.getClaimedBy()))")
    @Mapping(target = "claimedByNames", expression = "java(extractMemberNames(foundItem.getClaimedBy()))")
    FoundItemResponse toResponse(FoundItem foundItem);

    @Mapping(source = "reportedBy.memberId", target = "userId")
    @Mapping(source = "reportedBy.name", target = "userName")
    LostItemResponse toResponse(LostItem lostItem);

    @Mapping(source = "postedBy.memberId", target = "postedById")
    @Mapping(source = "postedBy.name", target = "postedByName")
    NoticeResponse toResponse(Notice notice);

    // Helper methods to extract member IDs and names from lists
    default List<Long> extractMemberIds(List<Member> members) {
        if (members == null) return null;
        return members.stream()
                .map(Member::getMemberId)
                .toList();
    }

    default List<String> extractMemberNames(List<Member> members) {
        if (members == null) return null;
        return members.stream()
                .map(Member::getName)
                .toList();
    }
}
