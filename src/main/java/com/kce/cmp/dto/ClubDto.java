package com.kce.cmp.dto;

import com.kce.cmp.model.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClubDto {
    private long id;
    private String name;
    private String description;
    private String category;
    private User lead;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
