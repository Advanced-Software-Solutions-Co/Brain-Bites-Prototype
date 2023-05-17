package com.ass.brainbitesprototype.services;

import com.ass.brainbitesprototype.dtos.ClassroomDto;
import com.ass.brainbitesprototype.models.Classroom;

import java.util.List;

public interface ClassroomService {
    List<ClassroomDto> findAllClassrooms();
    ClassroomDto findClassroomById(long classroomId);

    Classroom saveClassroom(ClassroomDto classroomDto);
}
