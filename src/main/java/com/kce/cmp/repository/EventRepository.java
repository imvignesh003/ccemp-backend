package com.kce.cmp.repository;

import com.kce.cmp.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByClubId(Long clubId);
}
