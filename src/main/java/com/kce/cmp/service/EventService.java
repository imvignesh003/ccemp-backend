package com.kce.cmp.service;

import com.kce.cmp.dto.EventDto;
import com.kce.cmp.dto.request.CreateEventRequest;
import com.kce.cmp.dto.response.EventRegistrationResponse;

import java.util.List;

public interface EventService {
    EventDto createEvent(CreateEventRequest createEventRequest);

    List<EventDto> getAllEvents();

    EventDto getEventById(Long eventId);

    List<EventDto> getEventsByClubId(Long clubId);

    List<EventRegistrationResponse> getEventRegistrations(Long eventId);

    List<String> getUserEventRegistrations(Long userId);

    boolean registerForEvent(Long eventId, Long userId);

    boolean unRegisterForEvent(Long eventId, Long userId);
}
