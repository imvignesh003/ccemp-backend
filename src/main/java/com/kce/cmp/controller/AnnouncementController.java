package com.kce.cmp.controller;

import com.kce.cmp.dto.AnnouncementDto;
import com.kce.cmp.dto.request.CreateAnnouncementRequest;
import com.kce.cmp.model.announcement.Announcement;
import com.kce.cmp.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody CreateAnnouncementRequest createAnnouncementRequest) {
        AnnouncementDto createdAnnouncement = announcementService.createAnnouncement(createAnnouncementRequest);
        return ResponseEntity.ok(createdAnnouncement);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(){
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }
    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<AnnouncementDto>> getAnnouncementsByClubId( @PathVariable Long clubId) {
        List<AnnouncementDto> announcements = announcementService.getAnnouncementsByClubId(clubId);
        return ResponseEntity.ok(announcements);
    }

}
