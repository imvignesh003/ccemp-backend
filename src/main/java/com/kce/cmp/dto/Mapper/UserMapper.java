package com.kce.cmp.dto.Mapper;

import com.kce.cmp.model.user.Profile;
import com.kce.cmp.model.user.User;

public class UserMapper {
    public static Profile toProfile(User user){
        return Profile.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contact(user.getContact())
                .role(user.getRole())
                .build();
    }
}
