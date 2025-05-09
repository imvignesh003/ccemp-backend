package com.kce.cmp.dto;

import com.kce.cmp.model.club.Club;
import com.kce.cmp.model.club.MemberStatus;
import com.kce.cmp.model.user.Profile;
import com.kce.cmp.model.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MemberDto {
    private long id;
    private Profile profile;
    private ClubDto clubDto;
    private MemberStatus status;
    private LocalDate joinedDate;
}
