package com.kce.cmp.service.impl;

import com.kce.cmp.dto.AnnouncementDto;
import com.kce.cmp.dto.Mapper.AnnouncementMapper;
import com.kce.cmp.dto.request.CreateAnnouncementRequest;
import com.kce.cmp.model.announcement.Announcement;
import com.kce.cmp.model.club.Club;
import com.kce.cmp.repository.AnnouncementRepository;
import com.kce.cmp.repository.ClubRepository;
import com.kce.cmp.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final ClubRepository clubRepository;

    @Override
    public AnnouncementDto createAnnouncement(CreateAnnouncementRequest createAnnouncementRequest) {
        Announcement announcement = new Announcement();
        announcement.setTitle(createAnnouncementRequest.getTitle());
        announcement.setContent(createAnnouncementRequest.getContent());
        Club club = clubRepository.findClubById(createAnnouncementRequest.getClubId());
        announcement.setClub(club);
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return AnnouncementMapper.map(savedAnnouncement);
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        List<AnnouncementDto> announcementDtos = new ArrayList<>();
        for (Announcement announcement : announcements) {
            announcementDtos.add(AnnouncementMapper.map(announcement));
        }
        return announcementDtos;
    }

    @Override
    public List<AnnouncementDto> getAnnouncementsByClubId(Long clubId) {
        List<Announcement> announcements = announcementRepository.findByClubId(clubId);
        List<AnnouncementDto> announcementDtos = new ArrayList<>();
        for (Announcement announcement : announcements) {
            announcementDtos.add(AnnouncementMapper.map(announcement));
        }
        return announcementDtos;
    }

}
