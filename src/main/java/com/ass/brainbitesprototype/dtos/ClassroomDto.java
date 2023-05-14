package com.ass.brainbitesprototype.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassroomDto {
    private Long id;
    //@NotEmpty(message = "Club title should not be empty")
    private String title;
    //@NotEmpty(message = "Photo link should not be empty")
    private String photoUrl;
    //@NotEmpty(message = "Content should not be empty")
    private String content;
}
