package com.kce.cmp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventRegistrationResponse {
    private long userId;
    private String userName;
}
