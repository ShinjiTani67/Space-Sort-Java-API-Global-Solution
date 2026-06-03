package com.space.sort.fiap.repository;

import java.util.Optional;
import java.util.UUID;

import com.space.sort.fiap.entity.Civil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CivilRepository extends JpaRepository<Civil,UUID> {

    Optional<Civil> findByUuid(UUID uuid);
    Optional<Civil> findByEmail(String email);
}
