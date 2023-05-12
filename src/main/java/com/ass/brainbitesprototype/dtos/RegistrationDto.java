package com.ass.brainbitesprototype.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordConfirm;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
