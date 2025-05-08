package com.kce.cmp.dto.request;

import lombok.Data;

@Data
public class CreateClubRequest {
    private String name;
    private String description;
    private String category;
    private Long leadId;
}
