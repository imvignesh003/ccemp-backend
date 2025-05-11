package com.kce.cmp.controller;

import com.kce.cmp.dto.EventDto;
import com.kce.cmp.dto.request.CreateEventRequest;
import com.kce.cmp.dto.request.JoinRequest;
import com.kce.cmp.dto.response.EventRegistrationResponse;
import com.kce.cmp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody CreateEventRequest createEventRequest){
        System.out.println("Creating event: " + createEventRequest.toString());
        return ResponseEntity.ok(eventService.createEvent(createEventRequest));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long eventId) {
        System.out.println("Fetching event with ID: " + eventId);
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        System.out.println("Fetching all events");
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<Boolean> registerForEvent(@PathVariable Long eventId, @RequestBody JoinRequest joinRequest) {
        System.out.println("User " + joinRequest.getUserId() + " registering for event: " + eventId);
        boolean success = eventService.registerForEvent(eventId, joinRequest.getUserId());
        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{eventId}/register")
    public ResponseEntity<Boolean> unregisterFromEvent(@PathVariable Long eventId, @RequestBody JoinRequest joinRequest) {
        System.out.println("User " + joinRequest.getUserId() + " unregistering from event: " + eventId);
        boolean success = eventService.unRegisterForEvent(eventId, joinRequest.getUserId());
        return ResponseEntity.ok(success);
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<EventDto>> getEventsByClubId(@PathVariable Long clubId) {
        System.out.println("Fetching events for club ID: " + clubId);
        return ResponseEntity.ok(eventService.getEventsByClubId(clubId));
    }


    @GetMapping("/registrations/{eventId}")
    public ResponseEntity<List<EventRegistrationResponse>> getEventRegistrations(@PathVariable Long eventId) {
        System.out.println("Fetching registrations for event ID: " + eventId);
        return ResponseEntity.ok(eventService.getEventRegistrations(eventId));
    }

    @GetMapping("/registrations/user/{userId}")
    public ResponseEntity<List<String>> getUserEventRegistrations(@PathVariable Long userId) {
        System.out.println("Fetching registrations for user ID: " + userId);
        List<String> registrations = eventService.getUserEventRegistrations(userId);
        for(String registration : registrations) {
            System.out.println("Event ID: " + registration);
        }
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registrations/{eventId}/count")
    public ResponseEntity<Integer> getEventRegistrationCount(@PathVariable Long eventId) {
        System.out.println("Fetching registration count for event ID: " + eventId);
        List<EventRegistrationResponse> registrations = eventService.getEventRegistrations(eventId);
        int count = registrations.size();
        return ResponseEntity.ok(count);
    }

}
