package com.kce.cmp.service;

import com.kce.cmp.dto.AnnouncementDto;
import com.kce.cmp.dto.request.CreateAnnouncementRequest;

import java.util.List;

public interface AnnouncementService {
    AnnouncementDto createAnnouncement(CreateAnnouncementRequest createAnnouncementRequest);

    List<AnnouncementDto> getAllAnnouncements();

    List<AnnouncementDto> getAnnouncementsByClubId(Long clubId);
}
