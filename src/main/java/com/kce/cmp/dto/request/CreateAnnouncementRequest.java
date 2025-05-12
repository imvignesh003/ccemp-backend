package com.kce.cmp.dto.request;

import lombok.Data;

@Data
public class CreateAnnouncementRequest {
    private String title;
    private String content;
    private Long clubId;
}
