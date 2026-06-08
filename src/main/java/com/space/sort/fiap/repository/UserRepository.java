package com.space.sort.fiap.repository;

import java.util.Optional;
import java.util.UUID;
import com.space.sort.fiap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,UUID> {

    Optional<User> findByUuid(UUID uuid);
    Optional<User> findByEmail(String email);
}
