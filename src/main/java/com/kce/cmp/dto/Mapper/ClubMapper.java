package com.kce.cmp.dto.Mapper;

import com.kce.cmp.dto.ClubDto;
import com.kce.cmp.model.club.Club;

public class ClubMapper {
    public static ClubDto map(Club club){
        return ClubDto.builder()
                .id(club.getId())
                .name(club.getName())
                .category(club.getCategory())
                .description(club.getDescription())
                .lead(club.getLead())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .build();

    }
}
