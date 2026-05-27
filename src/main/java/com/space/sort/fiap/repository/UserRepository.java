package com.space.sort.fiap.entity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID>{

    Optional<User> findByUuid(UUID uuid);
}
