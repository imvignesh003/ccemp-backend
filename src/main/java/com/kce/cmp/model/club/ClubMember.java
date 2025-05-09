package com.kce.cmp.model.club;

import com.kce.cmp.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "club_member")
@Data
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;
    private MemberStatus status;
    private LocalDate joinedDate;

    @PrePersist
    protected void onCreate() {
        this.joinedDate = LocalDate.now();
    }
}
