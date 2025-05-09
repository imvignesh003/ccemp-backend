package com.kce.cmp.controller;

import com.kce.cmp.dto.ClubDto;
import com.kce.cmp.dto.MemberDto;
import com.kce.cmp.dto.request.CreateClubRequest;
import com.kce.cmp.dto.request.JoinRequest;
import com.kce.cmp.model.club.Club;
import com.kce.cmp.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
@RequestMapping("/api/v1/clubs")public class ClubController {
    private final ClubService clubService;


    @GetMapping
    public ResponseEntity<List<ClubDto>> getClubs() {
        System.out.println("Inside ClubController");
        List<ClubDto> clubs = clubService.getAllClubs();
        return ResponseEntity.ok(clubs);
    }

    @PostMapping
    public ResponseEntity<ClubDto> createClub(@RequestBody CreateClubRequest createClubRequest) {
        System.out.println("Creating club: " + createClubRequest);
        ClubDto club = clubService.createClub(createClubRequest);
        return ResponseEntity.ok(club);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ClubDto>> getMyCLubs(@PathVariable Long id){
        System.out.println("Getting club of leader: "+id);
        List<ClubDto> myClubs = clubService.getMyClubs(id);
        return  ResponseEntity.ok(myClubs);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClubDto> updateClub(@PathVariable Long id, @RequestBody CreateClubRequest createClubRequest) {
        System.out.println("Updating club: " + id);
        ClubDto club = clubService.updateClub(id, createClubRequest);
        return ResponseEntity.ok(club);
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<Boolean> joinClub(@PathVariable Long id, @RequestBody JoinRequest joinRequest) {
        System.out.println("User " + joinRequest.getUserId() + " joining club: " + id);
        boolean success = clubService.joinClub(id, joinRequest.getUserId());
        return ResponseEntity.ok(success);
    }

    @GetMapping("/{id}/members/count")
    public ResponseEntity<Integer> getClubMembersCount(@PathVariable Long id) {
        System.out.println("Getting members count for club: " + id);
        Integer count = clubService.getClubMembersCount(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<MemberDto>> getClubMembers(@PathVariable Long id) {
        System.out.println("Getting members for club: " + id);
        List<MemberDto> members = clubService.getClubMembers(id);
        return ResponseEntity.ok(members);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Boolean> approveMember(@PathVariable Long id, @RequestBody JoinRequest joinRequest) {
        System.out.println("Approving member " + joinRequest.getUserId() + " for club: " + id);
        boolean success = clubService.approveMember(id, joinRequest.getUserId());
        return ResponseEntity.ok(success);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Boolean> rejectMember(@PathVariable Long id, @RequestBody JoinRequest joinRequest) {
        System.out.println("Rejecting member " + joinRequest.getUserId() + " for club: " + id);
        boolean success = clubService.rejectMember(id, joinRequest.getUserId());
        return ResponseEntity.ok(success);
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<Boolean> removeMember(@PathVariable Long id, @RequestBody JoinRequest joinRequest) {
        System.out.println("Removing member " + joinRequest.getUserId() + " from club: " + id);
        boolean success = clubService.rejectMember(id, joinRequest.getUserId());
        return ResponseEntity.ok(success);
    }
}
