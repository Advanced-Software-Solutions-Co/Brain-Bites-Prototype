package com.ass.brainbitesprototype.repositories;

import com.ass.brainbitesprototype.models.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SetRepository extends JpaRepository<Set, Long>{
    Optional<Set> findByTitle(String url);

    @Query("SELECT c from Set c WHERE LOWER(c.title) LIKE CONCAT('%', LOWER(:query), '%')")
    List<Set> searchSets(String query);
}
