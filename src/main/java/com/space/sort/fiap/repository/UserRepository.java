package com.space.sort.fiap.entity;

import java.util.Optional;
import java.util.UUID;
import com.space.sort.fiap.entity;

public interface UserRepository extends JpaRepository<User,UUID>{

    Optional<User> findByUuid(UUID uuid);
}
