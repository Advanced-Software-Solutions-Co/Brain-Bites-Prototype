package com.ass.brainbitesprototype.repositories;

import com.ass.brainbitesprototype.models.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetRepository extends JpaRepository<Set, Long>{
    Optional<Set> findByTitle(String url);
}
