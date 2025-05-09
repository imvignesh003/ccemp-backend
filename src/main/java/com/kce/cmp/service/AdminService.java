package com.kce.cmp.service;

import com.kce.cmp.model.user.Profile;
import com.kce.cmp.model.user.Role;

import java.util.List;

public interface AdminService {
    List<Profile> getAllProfiles();

    boolean updateRole(Long id, Role role);

    Profile getProfile(Long id);
}
