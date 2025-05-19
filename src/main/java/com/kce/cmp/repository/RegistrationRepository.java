package com.kce.cmp.repository;

import com.kce.cmp.dto.response.EventRegistrationResponse;
import com.kce.cmp.model.event.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<EventRegistration, Long> {
    List<EventRegistration> findEventIdsByUserId(Long userId);
    List<EventRegistration> findByEventId(Long eventId);
    EventRegistration findByEventIdAndUserId(Long eventId, Long userId);
    List<EventRegistration> findByUserId(Long userId);
}
