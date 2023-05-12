package com.ass.brainbitesprototype.services.impl;

import com.ass.brainbitesprototype.dtos.ClassroomDto;

import java.util.List;

public interface ClassroomService {
    List<ClassroomDto> findAllClassrooms();
    ClassroomDto findClassroomById(long classroomId);

}
