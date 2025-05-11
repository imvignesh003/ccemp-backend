package com.kce.cmp.service.impl;

import com.kce.cmp.dto.EventDto;
import com.kce.cmp.dto.Mapper.ClubMapper;
import com.kce.cmp.dto.request.CreateEventRequest;
import com.kce.cmp.dto.response.EventRegistrationResponse;
import com.kce.cmp.model.club.Club;
import com.kce.cmp.model.event.Event;
import com.kce.cmp.model.event.EventRegistration;
import com.kce.cmp.repository.ClubRepository;
import com.kce.cmp.repository.EventRepository;
import com.kce.cmp.repository.RegistrationRepository;
import com.kce.cmp.repository.UserRepository;
import com.kce.cmp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    @Override
    public EventDto createEvent(CreateEventRequest createEventRequest) {
        Event event = new Event();
        event.setTitle(createEventRequest.getTitle());
        event.setDescription(createEventRequest.getDescription());
        event.setLocation(createEventRequest.getLocation());
        event.setDateTime(createEventRequest.getDateTime());
        event.setMaxParticipants(createEventRequest.getMaxParticipants());
        Club club = clubRepository.findClubById(createEventRequest.getClubId());
        event.setClub(club);

        // Save the event to the database
        Event savedEvent = eventRepository.save(event);

        // Convert saved Event entity to EventDto
        return EventDto.builder()
                .id(savedEvent.getId())
                .title(savedEvent.getTitle())
                .description(savedEvent.getDescription())
                .location(savedEvent.getLocation())
                .dateTime(savedEvent.getDateTime())
                .club(ClubMapper.map(savedEvent.getClub()))
                .maxParticipants(savedEvent.getMaxParticipants())
                .build();
    }

    @Override
    public List<EventDto> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> EventDto.builder()
                        .id(event.getId())
                        .title(event.getTitle())
                        .description(event.getDescription())
                        .location(event.getLocation())
                        .dateTime(event.getDateTime())
                        .club(ClubMapper.map(event.getClub()))
                        .maxParticipants(event.getMaxParticipants())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public EventDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .location(event.getLocation())
                .dateTime(event.getDateTime())
                .club(ClubMapper.map(event.getClub()))
                .maxParticipants(event.getMaxParticipants())
                .build();
    }


    @Override
    public List<EventDto> getEventsByClubId(Long clubId) {
        List<Event> events = eventRepository.findByClubId(clubId);
        return events.stream()
                .map(event -> EventDto.builder()
                        .id(event.getId())
                        .title(event.getTitle())
                        .description(event.getDescription())
                        .location(event.getLocation())
                        .dateTime(event.getDateTime())
                        .club(ClubMapper.map(event.getClub()))
                        .maxParticipants(event.getMaxParticipants())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public List<EventRegistrationResponse> getEventRegistrations(Long eventId) {
        return registrationRepository.findByEventId(eventId).stream()
                .map(registration -> EventRegistrationResponse.builder()
                        .userId(registration.getUser().getId())
                        .userName(registration.getUser().getName())
                        .build())
                .toList();
    }

    @Override
    public List<String> getUserEventRegistrations(Long userId) {
        List<EventRegistration> events = registrationRepository.findEventIdsByUserId(userId);
        return events.stream()
                .map(event -> event.getEvent().getId()+"")
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean registerForEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        if (event.getMaxParticipants() <= 0) {
            return false; // Event is full
        }
        EventRegistration registration = registrationRepository.findByEventIdAndUserId(eventId, userId);
        if(registration != null) {
            return false; // User is already registered for this event
        }
        registration = new EventRegistration();
        registration.setEvent(event);
        registration.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        registrationRepository.save(registration);
        return true;
    }

    @Transactional
    @Override
    public boolean unRegisterForEvent(Long eventId, Long userId) {
        EventRegistration registration = registrationRepository.findByEventIdAndUserId(eventId, userId);
        if (registration != null) {
            registrationRepository.delete(registration);
            return true;
        }
        return false;
    }
}

