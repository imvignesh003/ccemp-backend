package com.kce.cmp.dto.Mapper;

import com.kce.cmp.dto.AnnouncementDto;
import com.kce.cmp.model.announcement.Announcement;

public class AnnouncementMapper {
    public static AnnouncementDto map(Announcement announcement) {
        return AnnouncementDto.builder()
                .id(announcement.getId())
                .title(announcement.getTitle())
                .content(announcement.getContent())
                .club(ClubMapper.map(announcement.getClub()))
                .createdAt(announcement.getCreatedAt())
                .build();
    }
}
