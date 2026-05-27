package com.space.sort.fiap.entity;

import java.util.Optional;
import java.util.UUID;
import com.space.sort.fiap.entity;

public interface SampleRepository extends JpaRepository<Sample,UUID>{

    Optional<Sample> findByUuid(UUID uuid);
}
