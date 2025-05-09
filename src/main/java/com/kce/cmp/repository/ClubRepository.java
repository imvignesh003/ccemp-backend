package com.kce.cmp.repository;

import com.kce.cmp.model.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    List<Club> findByLeadId(Long leadId);


}
