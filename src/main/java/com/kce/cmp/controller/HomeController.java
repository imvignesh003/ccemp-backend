package com.kce.cmp.controller;

import com.kce.cmp.model.user.Profile;
import com.kce.cmp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {
    private  final AdminService adminService;
    
    @GetMapping("/home")
    public String home() {
        return "Welcome to the Home Page!";
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        System.out.println("Inside AdminController");
        Profile profile = adminService.getProfile(id);
        return ResponseEntity.ok(profile);
    }
}
