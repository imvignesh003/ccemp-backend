package com.kce.cmp.service.impl;

import com.kce.cmp.dto.ClubDto;
import com.kce.cmp.dto.Mapper.ClubMapper;
import com.kce.cmp.dto.request.CreateClubRequest;
import com.kce.cmp.model.club.Club;
import com.kce.cmp.model.user.User;
import com.kce.cmp.repository.ClubRepository;
import com.kce.cmp.repository.UserRepository;
import com.kce.cmp.service.ClubService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

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
}
