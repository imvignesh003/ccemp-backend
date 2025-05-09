package com.kce.cmp.model.user;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Profile {
    private Long id;
    private String name;
    private String email;
    private String contact;
    private Role role;
}
