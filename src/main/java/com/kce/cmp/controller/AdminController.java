package com.kce.cmp.controller;

import com.kce.cmp.dto.request.RoleRequest;
import com.kce.cmp.model.user.Profile;
import com.kce.cmp.model.user.Role;
import com.kce.cmp.model.user.User;
import com.kce.cmp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getProfiles() {
        System.out.println("Inside AdminController");
        List<Profile> profiles = adminService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }
    @PutMapping("/profile/{id}")
    public ResponseEntity<Boolean> upddateRole(@PathVariable Long id, @RequestBody RoleRequest roleRequest) {
        System.out.println("Inside Update role"+ id + ":"+roleRequest);
        Boolean result = adminService.updateRole(id, roleRequest.getRole());
        return ResponseEntity.ok(result);
    }



    @GetMapping("/profile/leads")
    public ResponseEntity<List<Profile>> getLeads() {
        System.out.println("Inside AdminController");
        List<Profile> profiles = adminService.getAllProfiles();
        profiles = profiles.stream().filter((profile) -> profile.getRole() == Role.LEAD).toList();
        return ResponseEntity.ok(profiles);
    }
}
