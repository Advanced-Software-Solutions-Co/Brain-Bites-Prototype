package com.ass.brainbitesprototype.repositories;

import com.ass.brainbitesprototype.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
