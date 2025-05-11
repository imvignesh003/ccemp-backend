package com.kce.cmp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private ClubDto club;
    private int maxParticipants;
}
