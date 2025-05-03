package com.kce.cmp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lead")
@RequiredArgsConstructor
public class LeadController {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Lead");
    }
}
