package com.kce.cmp.service.impl;

import com.kce.cmp.dto.Mapper.UserMapper;
import com.kce.cmp.model.user.Profile;
import com.kce.cmp.model.user.Role;
import com.kce.cmp.model.user.User;
import com.kce.cmp.repository.UserRepository;
import com.kce.cmp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    @Override
    public List<Profile> getAllProfiles() {
        List<User> users = userRepository.findAll();
        List<Profile> profiles = new ArrayList<>();
        for(User user : users) {
            profiles.add(UserMapper.toProfile(user));
        }
        return profiles;
    }

    @Override
    public int getProfilesCount() {
        return (int) userRepository.count();
    }

    @Transactional
    @Override
    public boolean updateRole(Long id, Role role) {
        System.out.println("Inside AdminServiceImpl"+ id + ":"+role);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
           return false;
        }
        System.out.println(user);
        user.setRole(role);
        userRepository.save(user);
        return true;
    }


    @Override
    public Profile getProfile(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return UserMapper.toProfile(user);
    }
}
