package com.ass.brainbitesprototype.services;

import com.ass.brainbitesprototype.dtos.RegistrationDto;
import com.ass.brainbitesprototype.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
