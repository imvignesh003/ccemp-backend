package com.kce.cmp.controller;


import com.kce.cmp.repository.MemberRepository;
import com.kce.cmp.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final ClubService clubService;
    @GetMapping("/clubs/pending/{userId}")
    public ResponseEntity<Integer> getPendingClubsCount(@PathVariable Long userId) {
        return ResponseEntity.ok(clubService.getMyPendingclubs(userId));
    }
}
