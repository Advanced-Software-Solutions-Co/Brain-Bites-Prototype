package com.ass.brainbitesprototype.repositories;

import com.ass.brainbitesprototype.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByTitle(String url);
}
