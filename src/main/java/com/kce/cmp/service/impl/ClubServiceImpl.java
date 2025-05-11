package com.kce.cmp.service.impl;

import com.kce.cmp.dto.ClubDto;
import com.kce.cmp.dto.Mapper.ClubMapper;
import com.kce.cmp.dto.Mapper.UserMapper;
import com.kce.cmp.dto.MemberDto;
import com.kce.cmp.dto.request.CreateClubRequest;
import com.kce.cmp.model.club.Club;
import com.kce.cmp.model.club.ClubMember;
import com.kce.cmp.model.club.MemberStatus;
import com.kce.cmp.model.user.Profile;
import com.kce.cmp.model.user.User;
import com.kce.cmp.repository.ClubRepository;
import com.kce.cmp.repository.MemberRepository;
import com.kce.cmp.repository.UserRepository;
import com.kce.cmp.service.ClubService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ClubDto> getAllClubs() {
        System.out.println("Inside ClubServiceImpl");
        List<Club> clubs = clubRepository.findAll();
        List<ClubDto> res = new ArrayList<>();
        for(Club club:clubs){
            res.add(ClubMapper.map(club));
        }
        return res;
    }


    @Override
    public ClubDto createClub(@NonNull CreateClubRequest createClubRequest) {
        System.out.println("Creating club: " + createClubRequest);
        Club club = new Club();
        club.setName(createClubRequest.getName());
        club.setDescription(createClubRequest.getDescription());
        club.setCategory(createClubRequest.getCategory());
        User user = userRepository.findById(createClubRequest.getLeadId()).orElse(null);
        if(user == null) {
            return null;
        }
        club.setLead(user);
        return ClubMapper.map(clubRepository.save(club));
    }


    @Override
    public List<ClubDto> getMyClubs(Long id){
        System.out.println("Inside getMyClubs");
        List<ClubDto> list = new ArrayList<>();
        List<Club> clubs = clubRepository.findByLeadId(id);
        for(Club club:clubs){
            list.add(ClubMapper.map(club));
        }
        return list;
    }


    @Override
    public ClubDto updateClub(Long id, CreateClubRequest createClubRequest) {
        System.out.println("Updating club: " + id);
        Club club = clubRepository.findById(id).orElse(null);
        if (club == null) {
            return null;
        }
        club.setName(createClubRequest.getName());
        club.setDescription(createClubRequest.getDescription());
        club.setCategory(createClubRequest.getCategory());
        return ClubMapper.map(clubRepository.save(club));
    }


    @Override
    public int getClubMembersCount(Long id) {
        System.out.println("Getting members count for club: " + id);
        return memberRepository.countByClubId(id);
    }


    @Override
    public List<MemberDto> getClubMembers(Long id) {
        System.out.println("Getting members for club: " + id);
        List<ClubMember> members = memberRepository.findByClubId(id);
        List<MemberDto> memberDtos = new ArrayList<>();
        for (ClubMember member : members) {
            ClubDto clubDto = ClubMapper.map(member.getClub());
            Profile profile = UserMapper.toProfile(member.getUser());
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .profile(profile)
                    .clubDto(clubDto)
                    .status(member.getStatus())
                    .joinedDate(member.getJoinedDate())
                    .build();
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }

    @Override
    public boolean joinClub(Long clubId, Long userId) {
        System.out.println("User " + userId + " joining club: " + clubId);
        Club club = clubRepository.findById(clubId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (club == null || user == null) {
            return false;
        }
        ClubMember clubMember = new ClubMember();
        clubMember.setClub(club);
        clubMember.setUser(user);
        clubMember.setStatus(MemberStatus.PENDING);
        memberRepository.save(clubMember);
        return true;
    }

    @Override
    public boolean approveMember(Long clubId, Long userId) {
        System.out.println("Approving member " + userId + " for club: " + clubId);
        ClubMember clubMember = memberRepository.findByClubIdAndUserId(clubId, userId);
        if (clubMember == null) {
            return false;
        }
        clubMember.setStatus(MemberStatus.APPROVED);
        clubMember.setJoinedDate(LocalDate.now());
        memberRepository.save(clubMember);
        return true;
    }

    @Override
    public boolean rejectMember(Long clubId, Long userId) {
        System.out.println("Rejecting member " + userId + " for club: " + clubId);
        ClubMember clubMember = memberRepository.findByClubIdAndUserId(clubId, userId);
        if (clubMember == null) {
            return false;
        }
        memberRepository.delete(clubMember);
        return true;
    }


    @Override
    public String getClubJoiningStatus(Long clubId, Long userId) {
        System.out.println("Getting joining status for user " + userId + " in club: " + clubId);
        ClubMember clubMember = memberRepository.findByClubIdAndUserId(clubId, userId);
        if (clubMember == null) {
            return "NOT_A_MEMBER";
        }
        return clubMember.getStatus().name();
    }


}
