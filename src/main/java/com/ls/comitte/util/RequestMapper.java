package com.ls.comitte.util;

import com.ls.comitte.model.entity.*;
import com.ls.comitte.model.request.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "memberId", ignore = true)
    Member toEntity(MemberRequest memberRequest);

    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "comitteId", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "bidsCount", ignore = true)
    Comitte toEntity(ComitteRequest comitteRequest);

    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "bidId", ignore = true)
    @Mapping(target = "comitte", ignore = true)
    @Mapping(target = "finalBidder", ignore = true)
    @Mapping(target = "comitteNumber", ignore = true)
    @Mapping(target = "monthlyShare", ignore = true)
    Bid toEntity(BidRequest bidRequest);

    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comitte", ignore = true)
    @Mapping(target = "member", ignore = true)
    ComitteMemberMap toEntity(ComitteMemberMapRequest comitteMemberMapRequest);
}