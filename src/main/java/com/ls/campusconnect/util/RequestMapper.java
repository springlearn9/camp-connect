package com.ls.campusconnect.util;

import com.ls.campusconnect.model.entity.*;
import com.ls.campusconnect.model.request.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "postedBy", ignore = true)
    Event toEntity(EventRequest eventRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "reportedBy", ignore = true)
    @Mapping(target = "claimedBy", ignore = true)
    @Mapping(target = "claimedAt", ignore = true)
    FoundItem toEntity(FoundItemRequest foundItemRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "reportedBy", ignore = true)
    LostItem toEntity(LostItemRequest lostItemRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "postedBy", ignore = true)
    Notice toEntity(NoticeRequest noticeRequest);
}
