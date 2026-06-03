package com.space.sort.fiap.repository;

import java.util.Optional;
import java.util.UUID;
import com.space.sort.fiap.entity.Astronaut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Astronaut,UUID> {

    Optional<Astronaut> findByUuid(UUID uuid);
}
