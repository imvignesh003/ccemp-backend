package com.kce.cmp.repository;


import com.kce.cmp.model.club.Club;
import com.kce.cmp.model.club.ClubMember;
import com.kce.cmp.model.club.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<ClubMember, Long> {
    int countByClubId(Long clubId);
    List<ClubMember> findByClubId(Long clubId);
    ClubMember findByClubIdAndUserId(Long clubId, Long userId);
    List<ClubMember> findByUserId(Long userId);
    Integer countByStatus(MemberStatus memberStatus);
    Integer countByStatusAndUserId(MemberStatus memberStatus, Long userId);
}
