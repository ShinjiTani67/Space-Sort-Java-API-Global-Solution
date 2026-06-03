package com.space.sort.fiap.repository;

import java.util.Optional;
import java.util.UUID;
import com.space.sort.fiap.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CivilRepository extends JpaRepository<Sample,UUID> {

    Optional<Sample> findByUuid(UUID uuid);
}
