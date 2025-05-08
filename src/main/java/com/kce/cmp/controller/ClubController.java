package com.kce.cmp.controller;

import com.kce.cmp.dto.ClubDto;
import com.kce.cmp.dto.request.CreateClubRequest;
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
}
