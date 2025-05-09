package com.kce.cmp.service;

import com.kce.cmp.dto.ClubDto;
import com.kce.cmp.dto.MemberDto;
import com.kce.cmp.dto.request.CreateClubRequest;
import com.kce.cmp.model.club.Club;
import lombok.NonNull;

import java.util.List;

public interface ClubService {
    List<ClubDto> getAllClubs();

    ClubDto createClub(@NonNull CreateClubRequest createClubRequest);

    List<ClubDto> getMyClubs(Long id);

    ClubDto updateClub(Long id, CreateClubRequest createClubRequest);

    int getClubMembersCount(Long id);

    List<MemberDto> getClubMembers(Long id);

    boolean joinClub(Long clubId, Long userId);

    boolean approveMember(Long clubId, Long userId);

    boolean rejectMember(Long clubId, Long userId);
}
