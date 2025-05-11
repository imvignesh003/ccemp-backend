package com.kce.cmp.dto.request;

import com.kce.cmp.model.club.Club;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEventRequest {
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private Long clubId;
    private int maxParticipants;
}
