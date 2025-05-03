package com.kce.cmp.repository;

import com.kce.cmp.model.user.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kce.cmp.model.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);
}
