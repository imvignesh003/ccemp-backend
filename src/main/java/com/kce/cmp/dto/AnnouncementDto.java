package com.kce.cmp.dto;

import com.kce.cmp.model.club.Club;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AnnouncementDto {
    private Long id;
    private String title;
    private String content;
    private ClubDto club;
    private LocalDateTime createdAt;
}
