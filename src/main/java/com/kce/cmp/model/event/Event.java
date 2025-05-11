package com.kce.cmp.model.event;

import com.kce.cmp.model.club.Club;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private LocalDate createdDate;
    private int maxParticipants;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now();
    }

}
