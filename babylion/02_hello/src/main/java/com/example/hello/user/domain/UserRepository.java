package com.example.hello.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByNickname(String nickname);
    Optional<User> findByNickname(String nickname);
}
